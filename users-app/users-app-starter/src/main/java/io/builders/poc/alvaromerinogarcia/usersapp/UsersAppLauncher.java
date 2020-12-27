package io.builders.poc.alvaromerinogarcia.usersapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class UsersAppLauncher {
    public static void main(String[] args) {
        SpringApplication.run(UsersAppLauncher.class);
    }
}
