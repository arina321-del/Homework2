package com.homework2.userservice.dto;

public class UserEvent {

    private String operation;
    private String email;

    public UserEvent() {}

    public UserEvent(String operation, String email) {
        this.operation = operation;
        this.email = email;
    }

    public String getOperation() {
        return operation;
    }

    public String getEmail() {
        return email;
    }
}