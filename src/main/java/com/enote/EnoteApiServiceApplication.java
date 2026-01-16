package com.enote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.enote.config.AuditorConfig;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "awareRef")
public class EnoteApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnoteApiServiceApplication.class, args);
	}
	
	 @Bean("awareRef")
	    public AuditorAware<Integer> auditorProvider() {
	        return new AuditorConfig();
	    }
}
