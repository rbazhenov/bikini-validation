package org.example.service;

import lombok.AllArgsConstructor;
import org.example.event.CheckEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import www.ru.bikini_validation.api.swagger.model.ResidentCheckResponse;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CheckService {

    private final ApplicationEventPublisher eventPublisher;

    public ResidentCheckResponse check(String internalId) {
        String checkId = generateCheckId();

        ResidentCheckResponse response = new ResidentCheckResponse();
        response.setInternalId(internalId);
        response.setCheckId(checkId);

        eventPublisher.publishEvent(new CheckEvent(this, internalId, checkId));
        return response;
    }

    private String generateCheckId() {
        return UUID.randomUUID().toString();
    }
}
