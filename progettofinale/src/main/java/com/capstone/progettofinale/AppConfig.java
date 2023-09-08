package com.capstone.progettofinale;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.capstone.progettofinale.model.User;
import com.capstone.progettofinale.model.UserRole;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
@EnableAsync
public class AppConfig extends WebMvcConfigurationSupport {

	@Value("${admin.username}")
	private String adminUsername;

	@Value("${admin.password}")
	private String adminPassword;

	@Autowired
	PasswordEncoder encoder;

	@Bean
	public User getAdmin() {
		return new User(adminUsername, encoder.encode(adminPassword), "Super", "Admin",
				LocalDate.now().minus(30, ChronoUnit.YEARS), UserRole.ADMIN);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	            registry.addResourceHandler("/**")
	                 .addResourceLocations("classpath:/static/");
	    }
}
