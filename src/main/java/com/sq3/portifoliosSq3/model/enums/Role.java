package com.sq3.portifoliosSq3.model.enums;

public enum Role {
    USER("user");
    private String role;
    Role(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
