package com.kilid.stagetwo;

import com.kilid.stagetwo.model.User;
import com.kilid.stagetwo.model.User_Role;
import com.kilid.stagetwo.resource.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StagetwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StagetwoApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(UserService userService){
		return args -> {
			userService.save(new User(null,"user" ,"davood@gmail.com" ,"$2a$04$jMGYfNYKZI0S0iruz15Sb.QWkHVYgu43VXIcsWWu32ZjqOKbXUZve" ));

			userService.saveUserRole(new User_Role(null , 1 , "ROLE_ADMIN"));
		};
	}
}
