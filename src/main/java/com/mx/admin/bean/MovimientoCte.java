package com.mx.admin.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MovimientoCte implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idMovimientoCte;
	private Date fecha;
	private String concepto;
	private BigDecimal monto;
	private Integer tipoMovimiento;
	private Integer idCliente;
	private String nombreCliente;

	public Integer getIdMovimientoCte() {
		return idMovimientoCte;
	}

	public void setIdMovimientoCte(Integer idMovimientoCte) {
		this.idMovimientoCte = idMovimientoCte;
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

	public Integer getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
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
		return "MovimientoCte [idMovimientoCte=" + idMovimientoCte + ", fecha=" + fecha + ", concepto=" + concepto
				+ ", monto=" + monto + ", tipoMovimiento=" + tipoMovimiento + ", idCliente=" + idCliente
				+ ", nombreCliente=" + nombreCliente + "]";
	}

}
