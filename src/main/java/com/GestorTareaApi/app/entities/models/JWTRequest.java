package com.GestorTareaApi.app.entities.models;

import jakarta.validation.constraints.NotBlank;

public class JWTRequest {


    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public JWTRequest() {
    }


    public JWTRequest(String username, String password) {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
