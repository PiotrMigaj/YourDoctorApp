package pl.migibud.yourDoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class YourDoctorApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourDoctorApplication.class, args);
	}

}
