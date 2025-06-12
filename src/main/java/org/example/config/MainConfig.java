package org.example.config;

import org.example.controller.BpController;
import org.example.entity.BpEntity;
import org.example.respository.BpRepository;
import org.example.service.BpService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableKafka
@EnableAsync
@EntityScan(basePackageClasses = BpEntity.class)
@EnableMongoRepositories(basePackageClasses = BpRepository.class)
@ComponentScan(basePackageClasses = {
        BpService.class,
        BpController.class,
})
@Import(KafkaConfig.class)
@EnableConfigurationProperties
public class MainConfig {
}
