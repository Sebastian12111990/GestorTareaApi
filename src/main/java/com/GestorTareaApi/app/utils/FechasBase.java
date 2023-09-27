package com.GestorTareaApi.app.utils;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public abstract class FechasBase {
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        updatedAt = new Date(); // en caso de que quieras que ambos campos tengan la misma fecha inicialmente
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }
}
