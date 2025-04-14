package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Welcome to Bikini bottom validation!");
    }

}
