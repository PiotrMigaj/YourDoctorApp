package pl.migibud.yourDoctor.security.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.yourDoctor.security.user.SecurityUser;
import pl.migibud.yourDoctor.security.user.dto.SecurityUserDto;

@Mapper(componentModel = "spring", imports = {java.util.stream.Collectors.class})
public interface SecurityUserMapper {
    @Mappings(value = {
            @Mapping(source = "id", target="id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(expression = "java(securityUser.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))", target = "roles")
    })
    SecurityUserDto mapSecurityUserToSecurityUserDto(SecurityUser securityUser);
}
