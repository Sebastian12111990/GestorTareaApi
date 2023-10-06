package com.GestorTareaApi.app.entities;

import com.GestorTareaApi.app.utils.FechasBase;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario" , schema = "public")
public class Usuario extends FechasBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;


    @JsonManagedReference
    @OneToMany(mappedBy="usuario")
    private List<UsuarioTarea> usuarioTareas;
    @OneToMany(mappedBy = "usuario" , fetch = FetchType.EAGER)
    private List<UsuarioPerfil> usuarioPerfiles;

    public Usuario() {

    }

    public Usuario(String nombre, String apellido, String password, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmaiil(String email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UsuarioTarea> getUsuarioTareas() {
        return usuarioTareas;
    }

    public void setUsuarioTareas(List<UsuarioTarea> usuarioTareas) {
        this.usuarioTareas = usuarioTareas;
    }

    public List<UsuarioPerfil> getUsuarioPerfiles() {
        return usuarioPerfiles;
    }

    public void setUsuarioPerfiles(List<UsuarioPerfil> usuarioPerfiles) {
        this.usuarioPerfiles = usuarioPerfiles;
    }
}
