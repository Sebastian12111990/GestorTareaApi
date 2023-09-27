package com.GestorTareaApi.app.controllers;

import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.entities.models.JWTRequest;
import com.GestorTareaApi.app.entities.models.JWTResponse;
import com.GestorTareaApi.app.security.JwtTokenService;
import com.GestorTareaApi.app.security.JwtUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class AuthController {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private JwtTokenService jwtService;



    @PostMapping("/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request){

        this.auntheticate(request);

        final var userDetails = this.jwtUserDetailService.loadUserByUsername(request.getUsername());

        final String token = this.jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new JWTResponse(token , request.getUsername()));

    }



    private void auntheticate(JWTRequest request) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        }catch(BadCredentialsException | DisabledException ex1) {
            throw new RuntimeException(ex1.getMessage());
        }
    }


}
