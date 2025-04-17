package org.example.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.ResidentCheckAnswer;
import org.example.properties.KafkaProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@ConditionalOnProperty(
        prefix = "kafka",
        name = "enabled",
        havingValue = "true"
)
@AllArgsConstructor
public class KafkaConfig {

    private final KafkaProperties properties;

    static ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean("checkKafkaTemplate")
    public KafkaTemplate<String, ResidentCheckAnswer> checkKafkaTemplate(
            @Qualifier("checkProducerFactory") ProducerFactory<String, ResidentCheckAnswer> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean("checkProducerFactory")
    public ProducerFactory<String, ResidentCheckAnswer> checkProducerFactory() {
        return new DefaultKafkaProducerFactory<>(
                propsConfigs(),
                new StringSerializer(),
                new JsonSerializer<>(objectMapper()));
    }

    Map<String, Object> propsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getServer());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, properties.getClientId());
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        props.put(ProducerConfig.RETRIES_CONFIG, 5);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
}
