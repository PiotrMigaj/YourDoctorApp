package pl.migibud.yourDoctor.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.migibud.yourDoctor.security.user.mapper.SecurityUserMapper;

@Slf4j
@Configuration
@RequiredArgsConstructor
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfiguration {

    private final ObjectMapper objectMapper;
    private final UserDetailsService userDetailsService;
    private final SecurityUserMapper securityUserMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(objectMapper, authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), securityUserMapper);
        authenticationFilter.setFilterProcessesUrl("/auth/login");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .addFilter(authenticationFilter)
                .addFilterAfter(new JwtAuthorizationFilter(), AuthenticationFilter.class)
                .authorizeRequests((auth) -> auth
                                .antMatchers("/auth/login").permitAll()
                                .antMatchers("/api/test/anyone").authenticated()
                                .antMatchers("/api/test/doctor").hasRole("DOCTOR")
                                .antMatchers("/api/test/admin").hasRole("ADMIN")
                                .antMatchers("/api/test/secured").permitAll()
                                .antMatchers("/api/doctor/recently-joined").permitAll()
//                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider());
        return http.build();
    }
}
