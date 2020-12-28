package io.builders.poc.alvaromerinogarcia.depositsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableTransactionManagement
@EnableEurekaClient
public class DepositsAppLauncher {
    public static void main(String[] args) {
        SpringApplication.run(DepositsAppLauncher.class);
    }
}
