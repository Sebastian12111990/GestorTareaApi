package com.GestorTareaApi.app.entities;

import com.GestorTareaApi.app.utils.FechasBase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "tareas" , schema = "public")
public class Tarea extends FechasBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    @Column(name = "titulo")
    private String titulo;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "fecha_de_vencimiento")
    private Timestamp  fechaDevencimiento;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoTarea estado;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy="tarea")
    private List<UsuarioTarea> usuarioTareas;

    public Tarea() {

    }

    public Tarea(String titulo, EstadoTarea estado) {
        this.titulo = titulo;
        this.estado = estado;
    }

    public Tarea(Long id, String titulo, Timestamp fechaDevencimiento, EstadoTarea estado) {
        this.id = id;
        this.titulo = titulo;
        this.fechaDevencimiento = fechaDevencimiento;
        this.estado = estado;
    }

    public Tarea(String titulo, Timestamp fechaDevencimiento, EstadoTarea estado) {
        this.titulo = titulo;
        this.fechaDevencimiento = fechaDevencimiento;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Timestamp getFechaDevencimiento() {
        return fechaDevencimiento;
    }

    public void setFechaDevencimiento(Timestamp  fechaDevencimiento) {
        this.fechaDevencimiento = fechaDevencimiento;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public List<UsuarioTarea> getUsuarioTareas() {
        return usuarioTareas;
    }

    public void setUsuarioTareas(List<UsuarioTarea> usuarioTareas) {
        this.usuarioTareas = usuarioTareas;
    }
}
