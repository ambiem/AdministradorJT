package com.mx.admin.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idCliente;
	private Date fecha;
	private String nombre;
	private String concepto;
	private BigDecimal porcentaje;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", fecha=" + fecha
				+ ", nombre=" + nombre + ", concepto=" + concepto
				+ ", porcentaje="
				+ porcentaje + "]";
	}

}
