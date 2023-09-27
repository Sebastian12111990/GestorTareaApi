package com.GestorTareaApi.app.entities;


import com.GestorTareaApi.app.utils.FechasBase;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario_tareas" ,schema = "public")
public class UsuarioTarea  extends FechasBase  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tarea;

    public UsuarioTarea() {

    }
    public UsuarioTarea(Usuario usuario, Tarea tarea) {
        this.usuario = usuario;
        this.tarea = tarea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}
