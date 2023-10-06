package com.GestorTareaApi.app;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private  Map<String , String> messages;
    public GlobalExceptionHandler(){
        messages = new HashMap<>();
    }
    public Map<String, String> getMessages() {
        return messages;
    }
    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
    public void addMessages(String llave , String mensaje){
        messages.put(llave , mensaje);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleFeignUnauthorizedException(DataIntegrityViolationException e) {
        this.addMessages("message" ,  "No existe relacions");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getMessages());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeExceptionException(RuntimeException e) {
        this.addMessages("message" ,  "Credenciales Invalidas");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getMessages());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        this.addMessages("message" ,  "Formato de Propiedad invalido -> " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getMessages());
    }


}