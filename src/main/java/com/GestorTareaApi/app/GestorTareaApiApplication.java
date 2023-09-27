package com.GestorTareaApi.app;

import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.entities.UsuarioPerfil;
import com.GestorTareaApi.app.repositories.UsuarioPerfilRepository;
import com.GestorTareaApi.app.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@SpringBootApplication
public class GestorTareaApiApplication {
	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(GestorTareaApiApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner initData(UsuarioRepository usuarioRepository) {
		return args -> {

			if (usuarioRepository.count() == 0) { //se ejecutara solo una vez cuando el sistema recien se levante

				Usuario usuario1 = usuarioRepository.save(new Usuario("Sebastian" , "Perez"   , passwordEncoder.encode("12345") , "seba@gmail.com"   ));
				Usuario usuario2 = usuarioRepository.save(new Usuario("Luis"      , "Pruebas" , passwordEncoder.encode("12345") , "pruebas@gmail.com"));

			}

		};
	}

}
