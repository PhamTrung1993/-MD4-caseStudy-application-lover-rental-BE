package com.codegym.controller.avatar;

public class AvatarUploadResponse {
    private String message;

    public AvatarUploadResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
