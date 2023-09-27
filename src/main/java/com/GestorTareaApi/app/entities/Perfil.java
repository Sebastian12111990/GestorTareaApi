package com.GestorTareaApi.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.GestorTareaApi.app.utils.FechasBase;
import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;


@Entity
@Table(name = "perfil" , schema="public")
public class Perfil extends FechasBase implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_perfil")
    private String nombrePerfil;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "perfil")
    private List<UsuarioPerfil> usuarioPerfiles;
    public Perfil() {

    }


    public Perfil(String nombrePerfil, String description) {
        this.nombrePerfil = nombrePerfil;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombrePerfil() {
        return nombrePerfil;
    }
    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<UsuarioPerfil> getUsuarioPerfiles() {
        return usuarioPerfiles;
    }

    public void setUsuarioPerfiles(List<UsuarioPerfil> usuarioPerfiles) {
        this.usuarioPerfiles = usuarioPerfiles;
    }
}
