package com.GestorTareaApi.app.entities.models;

public class JwtTokenUser {
    private String email;
    private String jwt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
