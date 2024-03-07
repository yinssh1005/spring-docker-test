/*******************************************************************************
 Copyright (c) 2024 Me. All Rights Reserved.
 ******************************************************************************/

/**
 * Testing.
 */
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

    public String test;
    public Integer testIng;

    public static void main(String[] args) {

        String springTest = "SpringTest";
        System.out.println(springTest + "SpringTest");
        SpringApplication.run(
                SpringDockerTestApplication
                        .class,
                args);
    }

}