package com.example.demo.features.costumers.model;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public boolean isAdmin() {
        return this == ROLE_ADMIN;
    }
    
    public boolean isUser() {
        return this == ROLE_USER;
    }

    public static Role fromString(String roleStr) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleStr)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant for role: " + roleStr);
    }

    public String toString() {
        return this.name();
    }
}
