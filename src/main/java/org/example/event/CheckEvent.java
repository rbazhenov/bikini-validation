package org.example.event;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class CheckEvent extends ApplicationEvent {

    private final String internalId;
    private final String checkId;

    public CheckEvent(Object source, String internalId, String checkId) {
        super(source);
        this.internalId = internalId;
        this.checkId = checkId;
    }
}
