package com.mx.admin.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EntradaSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idOperacion;
	private Date fecha;
	private String concepto;
	private BigDecimal monto;
	private Integer tipoOperacion;
	private BigDecimal Saldo;
	private Integer idCliente;
	private String nombreCliente;

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Integer getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(Integer tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public BigDecimal getSaldo() {
		return Saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		Saldo = saldo;
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
		return "EntradaSalida [idOperacion=" + idOperacion + ", fecha=" + fecha
				+ ", concepto=" + concepto + ", monto=" + monto
				+ ", tipoOperacion=" + tipoOperacion + ", Saldo=" + Saldo
				+ ", idCliente=" + idCliente + ", nombreCliente="
				+ nombreCliente + "]";
	}

}
