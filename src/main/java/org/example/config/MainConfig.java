package org.example.config;

import org.example.controller.BpController;
import org.example.service.BpService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableKafka
@EnableAsync
@ComponentScan(basePackageClasses = {
        BpService.class,
        BpController.class,
})
@Import(KafkaConfig.class)
@EnableConfigurationProperties
public class MainConfig {
}
