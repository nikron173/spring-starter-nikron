package com.nikron.spring.database.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, OPERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
