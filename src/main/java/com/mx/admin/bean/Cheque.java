package com.mx.admin.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cheque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idCheque;
	private String concepto;
	private BigDecimal monto;
	private Integer idCliente;
	private String nombreCliente;
	private Date fecha;

	public Integer getIdCheque() {
		return idCheque;
	}

	public void setIdCheque(Integer idCheque) {
		this.idCheque = idCheque;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "Cheque [idCheque=" + idCheque + ", concepto=" + concepto
				+ ", monto=" + monto + ", idCliente=" + idCliente
				+ ", nombreCliente=" + nombreCliente + ", fecha=" + fecha + "]";
	}

}