package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@Import({AuthServerSecurityConfiguration.class, AuthorizationServerConfiguration.class})
@Controller
public class AuthorizationServer extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServer.class, args);
	}
}
