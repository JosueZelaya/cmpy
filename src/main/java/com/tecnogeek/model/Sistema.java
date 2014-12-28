package com.tecnogeek.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sistema", schema = "public")
public class Sistema{

	@Id
    @Column(name="ID")
    @GeneratedValue
	private Integer id;
	
	@Column(name="variable")
	private String variable;
	
	@Column(name="valor")
	private String valor;
	
	@Column(name="sis_activo")
	private Boolean estadoActivo;
	
	@Column(name="sis_fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="sis_fecha_modificacion")
	private Date fechaModificacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Boolean getEstadoActivo() {
		return estadoActivo;
	}

	public void setEstadoActivo(Boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
}
