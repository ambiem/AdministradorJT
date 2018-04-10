package com.mx.admin.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cotizacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idCotizacion;
	private Date fecha;
	private BigDecimal cantidad;
	private BigDecimal tc;
	private BigDecimal porcentaje;
	private BigDecimal totalPesos;
	private BigDecimal comision;
	private BigDecimal comisionExtra;
	private BigDecimal totalComisionExtra;
	private BigDecimal granTotal;
	private BigDecimal tcPonderado;

	private Integer idCliente;
	private String nombreCliente;
	private Integer idEmpresa;
	private String nombreEmpresa;

	public Integer getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Integer idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTc() {
		return tc;
	}

	public void setTc(BigDecimal tc) {
		this.tc = tc;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigDecimal getTotalPesos() {
		return totalPesos;
	}

	public void setTotalPesos(BigDecimal totalPesos) {
		this.totalPesos = totalPesos;
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public BigDecimal getComisionExtra() {
		return comisionExtra;
	}

	public void setComisionExtra(BigDecimal comisionExtra) {
		this.comisionExtra = comisionExtra;
	}

	public BigDecimal getTotalComisionExtra() {
		return totalComisionExtra;
	}

	public void setTotalComisionExtra(BigDecimal totalComisionExtra) {
		this.totalComisionExtra = totalComisionExtra;
	}

	public BigDecimal getGranTotal() {
		return granTotal;
	}

	public void setGranTotal(BigDecimal granTotal) {
		this.granTotal = granTotal;
	}

	public BigDecimal getTcPonderado() {
		return tcPonderado;
	}

	public void setTcPonderado(BigDecimal tcPonderado) {
		this.tcPonderado = tcPonderado;
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

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	@Override
	public String toString() {
		return "Cotizacion [idCotizacion=" + idCotizacion + ", fecha=" + fecha
				+ ", cantidad=" + cantidad + ", tc=" + tc + ", porcentaje="
				+ porcentaje + ", totalPesos=" + totalPesos + ", comision="
				+ comision + ", comisionExtra=" + comisionExtra
				+ ", totalComisionExtra=" + totalComisionExtra + ", granTotal="
				+ granTotal + ", tcPonderado=" + tcPonderado + ", idCliente="
				+ idCliente + ", nombreCliente=" + nombreCliente
				+ ", idEmpresa=" + idEmpresa + ", nombreEmpresa="
				+ nombreEmpresa + "]";
	}

}
