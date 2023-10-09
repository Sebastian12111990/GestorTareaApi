package com.GestorTareaApi.app.dtos;

import java.util.Map;

public class ResponseCustom<T> {

    private String status;

    private String message;

    private Integer statusCode;

    private T data;

    private Map<String , String> errorDetails;

    public ResponseCustom(String status, String message, Integer statusCode) {
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.data = null;
        this.errorDetails = null;
    }

    public ResponseCustom(String status, String message, Integer statusCode, T data, Map<String, String> errorDetails) {
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
        this.errorDetails = errorDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(Map<String, String> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
