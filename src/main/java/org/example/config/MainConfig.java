package org.example.config;

import org.example.controller.BpController;
import org.example.service.BpService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableKafka
@EnableAsync
@ComponentScan(basePackageClasses = {
        BpService.class,
        BpController.class,
})
public class MainConfig {
}
