package com.example.swapp;

import org.apache.skywalking.apm.meter.micrometer.SkywalkingMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SwappApplication {

    public static void main(String[] args) {

        SpringApplication.run(SwappApplication.class, args);
    }

    @Bean
    SkywalkingMeterRegistry skywalkingMeterRegistry() {
        return new SkywalkingMeterRegistry();
    }

}
