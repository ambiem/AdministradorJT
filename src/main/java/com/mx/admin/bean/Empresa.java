package com.mx.admin.bean;

import java.io.Serializable;
import java.util.Date;

public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idEmpresa;
	private Date fecha;
	private String nombre;
	private Integer idCliente;
	private String nombreCliente;

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", fecha=" + fecha
				+ ", nombre=" + nombre + ", idCliente=" + idCliente
				+ ", nombreCliente=" + nombreCliente + "]";
	}

}
