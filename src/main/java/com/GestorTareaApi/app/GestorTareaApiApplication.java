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

import java.util.Optional;

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

			if (usuarioRepository.count() > 1) { //solo es fines de prueba

				Optional<Usuario> usuarioOptional1  = usuarioRepository.findById(1L);
				Optional<Usuario> usuarioOptional2  = usuarioRepository.findById(2L);

				Usuario usuario1  = usuarioOptional1.get();
				Usuario usuario2  = usuarioOptional2.get();

				usuario1.setPassword(passwordEncoder.encode("12345"));
				usuario2.setPassword(passwordEncoder.encode("12345"));

				usuarioRepository.save(usuario1);
				usuarioRepository.save(usuario2);

			}

		};
	}

}
