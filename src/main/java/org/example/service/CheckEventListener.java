package org.example.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.event.CheckEvent;
import org.example.model.CheckResult;
import org.example.model.ResidentCheckAnswer;
import org.example.properties.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Setter(onMethod = @__({@Autowired(required = false)}))
public class CheckEventListener implements ApplicationListener<CheckEvent> {

    private KafkaTemplate<String, ResidentCheckAnswer> kafkaTemplate;
    private KafkaProperties properties;

    @Override
    public void onApplicationEvent(CheckEvent event) {
        String topic = properties.getTopic();

        ResidentCheckAnswer checkResult = new ResidentCheckAnswer();
        checkResult.setCheckId(event.getCheckId());
        checkResult.setResult(CheckResult.SUCCESS);

        kafkaTemplate.send(topic, checkResult.getCheckId(), checkResult);
        log.info("Check result sent to kafka: check Id = {}", checkResult.getCheckId());
    }
}
