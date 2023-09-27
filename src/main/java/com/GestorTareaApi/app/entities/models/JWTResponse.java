package com.GestorTareaApi.app.entities.models;


public class JWTResponse   {

    private String jwt;

    private String refreshToken;
    private String email;

    public JWTResponse() {
    }

    public JWTResponse(String jwt , String email) {
        this.jwt = jwt;
        this.email = email;
    }

    public JWTResponse(String jwt , String refreshToken ,String email) {
        this.jwt = jwt;
        this.email = email;
        this.refreshToken = refreshToken;
    }


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
