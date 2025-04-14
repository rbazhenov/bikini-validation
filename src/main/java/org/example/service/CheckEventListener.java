package org.example.service;

import lombok.AllArgsConstructor;
import org.example.event.CheckEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckEventListener implements ApplicationListener<CheckEvent> {

    @Override
    public void onApplicationEvent(CheckEvent event) {
        //todo rbs send message to kafka
    }
}
