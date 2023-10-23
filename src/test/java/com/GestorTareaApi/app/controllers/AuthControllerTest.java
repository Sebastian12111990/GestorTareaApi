package com.GestorTareaApi.app.controllers;


import com.GestorTareaApi.app.repositories.UsuarioRepository;
import com.GestorTareaApi.app.security.JwtTokenService;
import com.GestorTareaApi.app.security.JwtUserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private JwtUserDetailService jwtUserDetailService;
    @MockBean
    private JwtTokenService jwtService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testLoginEndpoint() throws Exception {

        String username = "seba@gmail.com";



        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

        UserDetails mockUserDetails = new User(username, "12345", authorities);

        when(jwtUserDetailService.loadUserByUsername(username)).thenReturn(mockUserDetails);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mock(Authentication.class));

        when(jwtService.generateToken(mockUserDetails)).thenReturn("dummy-jwt-token");


        mockMvc.perform(post("/authenticate")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"seba@gmail.com\", \"password\":\"12345\"}"))
                .andExpect(status().isOk());

    }

}
