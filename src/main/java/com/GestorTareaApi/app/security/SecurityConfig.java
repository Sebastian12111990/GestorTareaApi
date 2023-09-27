package com.GestorTareaApi.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    @Autowired
    SecurityFilterChain securityFilterChain(HttpSecurity http , JwtValidationFilter jWTValidationFilter) throws Exception {

        http.sessionManagement( sess ->  sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/tarea/**" ).hasAuthority("ROLE_ADMIN").requestMatchers("/tarea/" ).hasAuthority("ROLE_ADMIN").anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        //dejaremos el crsf desactivado para que no perjudique las pruebas de la autorizacion
        http.csrf(csrf -> csrf.disable());

        http.addFilterAfter(jWTValidationFilter, BasicAuthenticationFilter.class);

        //como las pruebas se realizaran localmente y no desde un un frontend o otro programa no activaremos cors

        return http.build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config ) throws Exception {
        return config.getAuthenticationManager();
    }

}
