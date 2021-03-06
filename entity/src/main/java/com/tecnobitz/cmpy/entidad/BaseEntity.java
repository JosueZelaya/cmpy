/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.entidad;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jzelaya
 * @param <ID>
 */
@MappedSuperclass
public abstract class BaseEntity<ID> {

    @Column(name = "creation_time", nullable = false,updatable=false)
    private Date creationTime;

    @Column(name = "modification_time", nullable = false)
    private Date modificationTime;
    
    @NotNull
    @Column(name = "sis_activo")
    private boolean sisActivo = true;

    @Version
    private long version;

    public abstract ID getId();

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public long getVersion() {
        return version;
    }
    
    public boolean isActive(){
        return this.sisActivo;
    }
    
    public void desactivar(){
        this.sisActivo = false;
    }
    
    public void activar(){
        this.sisActivo = true;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = new Date();
    }
}

