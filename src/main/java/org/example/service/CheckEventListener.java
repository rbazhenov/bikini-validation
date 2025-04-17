package org.example.service;

import lombok.Setter;
import org.example.event.CheckEvent;
import org.example.model.CheckResult;
import org.example.model.ResidentCheckAnswer;
import org.example.properties.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod = @__(@Autowired))
public class CheckEventListener implements ApplicationListener<CheckEvent> {

    private KafkaTemplate<String, ResidentCheckAnswer> kafkaTemplate;
    private KafkaProperties properties;

    @Autowired
    public void setCreateTemplate(
            @Qualifier("checkKafkaTemplate") KafkaTemplate<String, ResidentCheckAnswer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onApplicationEvent(CheckEvent event) {
        String topic = properties.getTopic();

        ResidentCheckAnswer checkResult = new ResidentCheckAnswer();
        checkResult.setCheckId(event.getCheckId());
        checkResult.setResult(CheckResult.SUCCESS);

        kafkaTemplate.send(topic, checkResult.getCheckId(), checkResult);
    }
}
