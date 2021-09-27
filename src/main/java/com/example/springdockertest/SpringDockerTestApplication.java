package com.example.springdockertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.example.springdockertest.*")
@EnableJpaRepositories
@SpringBootApplication
public class SpringDockerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDockerTestApplication.class, args);
    }

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }


}
