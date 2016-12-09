/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.entidad;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexander
 */

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ProductoPsKey implements Serializable{
    
    @Column
    private long id;
    
    @JsonManagedReference
    @JoinColumn(name = "tiendaps_id", nullable = false)
    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    private Tienda tienda;

    public ProductoPsKey(){
    }
    
    public ProductoPsKey(long producto, Tienda tienda) {
        super();
        this.id = producto;
        this.tienda = tienda;
    }
    
}
