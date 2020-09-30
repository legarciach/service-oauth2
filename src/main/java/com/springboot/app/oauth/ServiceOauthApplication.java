package com.springboot.app.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ServiceOauthApplication implements CommandLineRunner {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ServiceOauthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String pass = "12345";
        for (int i = 0; i <4 ; i++){
            String cifrado = passwordEncoder.encode(pass);
            System.out.println(cifrado);
        }
    }
}
