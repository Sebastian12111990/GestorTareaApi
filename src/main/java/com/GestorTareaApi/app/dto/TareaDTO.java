package com.GestorTareaApi.app.dto;

import com.GestorTareaApi.app.entities.EstadoTarea;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class TareaDTO {
    @NotBlank
    private String titulo;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private Timestamp fechaDevencimiento;
    @NotNull
    private EstadoTarea estado;


    public TareaDTO(String titulo, Timestamp fechaDevencimiento, EstadoTarea estado) {
        this.titulo = titulo;
        this.fechaDevencimiento = fechaDevencimiento;
        this.estado = estado;
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

    public void setFechaDevencimiento(Timestamp fechaDevencimiento) {
        this.fechaDevencimiento = fechaDevencimiento;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }
}
