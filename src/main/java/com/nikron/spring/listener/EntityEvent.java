package com.nikron.spring.listener;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EntityEvent extends ApplicationEvent {

    private AccessType accessType;
    public EntityEvent(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
}
