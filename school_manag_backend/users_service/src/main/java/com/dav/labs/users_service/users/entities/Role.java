package com.dav.labs.users_service.users.entities;

public enum Role {
    ADMIN("admin"),
    STUDENT("student"),
    TEACHER("teacher"),
    MANAGER("manager");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
