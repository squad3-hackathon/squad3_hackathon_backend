package com.sq3.portifoliosSq3.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER("user");


    private final String role;

    Role(String role) {
        this.role = role;
    }

}
