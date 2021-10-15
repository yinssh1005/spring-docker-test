package com.example.springdockertest;

import com.example.springdockertest.services.FooServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public CommandLineRunner run(FooServiceImpl fsi) {
        return (args) -> {
            System.out.println(fsi.findAll());
            System.out.println("CommandLineRunner.run has been executed.");
        };
    }

}
