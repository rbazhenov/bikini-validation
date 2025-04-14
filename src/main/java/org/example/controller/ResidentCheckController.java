package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.CheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import www.ru.bikini_validation.api.swagger.api.BikiniValidationApi;
import www.ru.bikini_validation.api.swagger.model.ResidentCheckResponse;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class ResidentCheckController implements BikiniValidationApi {

    private final CheckService service;

    @Override
    public ResponseEntity<ResidentCheckResponse> checkResident(@Valid String internalId) {
        return ResponseEntity.ok(service.check(internalId));
    }
}
