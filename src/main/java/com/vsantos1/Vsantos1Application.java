package com.vsantos1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Vsantos1Application {

    public static void main(String[] args) {

        // Only to get encrypted password on app startup
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var test = bCryptPasswordEncoder.encode("password");
        System.out.println(test);

        SpringApplication.run(Vsantos1Application.class, args);
    }

}
