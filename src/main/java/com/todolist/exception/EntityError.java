package com.todolist.exception;

public class EntityError {
    private String message;

    public EntityError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
