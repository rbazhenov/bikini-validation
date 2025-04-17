package org.example.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties("kafka")
public class KafkaProperties {
    private String topic;
    private String server;
    private String clientId;
}
