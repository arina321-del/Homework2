package org.example.notificationservice.dto;

public class UserEvent {

    private String operation;
    private String email;

    public String getOperation() {
        return operation;
    }

    public String getEmail() {
        return email;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}