package com.GestorTareaApi.app.entities;

import com.GestorTareaApi.app.utils.FechasBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "estado_tarea" ,schema = "public")
public class EstadoTarea extends FechasBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    @Column(name = "estado")
    private String estado;
    public EstadoTarea() {

    }
    public EstadoTarea(String estado) {
        this.estado = estado;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
