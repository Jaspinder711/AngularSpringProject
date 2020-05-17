package com.example.learn.angularspringdemo;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.learn.angularspringdemo.model.User;
import com.example.learn.angularspringdemo.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories("com.example.learn.angularspringdemo.repository")
@EntityScan("com.example.learn.angularspringdemo.model")
@ComponentScan({"com.example.learn.angularspringdemo.controller"})
public class AngularspringdemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AngularspringdemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args ->{
		  Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name->{
			 User user=new User(name, name.toLowerCase()+"@domain.com");
			 userRepository.save(user);
		  });	
		  userRepository.findAll().forEach(System.out::println);
		};
	}
	
	@Bean
	public CorsFilter corsFilter() {
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	CorsConfiguration config = new CorsConfiguration();
	config.setAllowCredentials(true);
	config.addAllowedOrigin("*");
	config.addAllowedHeader("*");
	config.addAllowedMethod("OPTIONS");
	config.addAllowedMethod("GET");
	config.addAllowedMethod("POST");
	config.addAllowedMethod("PUT");
	config.addAllowedMethod("DELETE");
	source.registerCorsConfiguration("/**", config);
	return new CorsFilter(source);
	}
}
