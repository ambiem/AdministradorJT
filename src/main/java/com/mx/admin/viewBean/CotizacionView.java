package com.mx.admin.viewBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

public class CotizacionView implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Integer idCotizacion;
	private Date fecha;
	private Integer idCliente;
	private Integer idEmpresa;
	private BigDecimal cantidad;
	private BigDecimal tc;
	private BigDecimal porcentaje;
	private BigDecimal totalPesos;
	private BigDecimal comision;
	private BigDecimal comisionExtra;
	private BigDecimal totalComisionExtra;
	private BigDecimal granTotal;
	private BigDecimal tcPonderado;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
		
		if( this.cantidad != null && this.tc != null){
			setTotalPesos(this.cantidad.multiply(this.tc, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
		}
		
		if(this.granTotal != null && this.cantidad != null){
			setTcPonderado(granTotal.divide(this.cantidad, 8, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_UP));
		}
	}

	public BigDecimal getTc() {
		return tc;
	}

	public void setTc(BigDecimal tc) {
		this.tc = tc;
		
		if(this.tc != null && this.comisionExtra != null){
			setTotalComisionExtra(this.tc.multiply(this.comisionExtra, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
		}
		
		if( this.cantidad != null && this.tc != null){
			setTotalPesos(this.cantidad.multiply(this.tc, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
		}
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
		
		if( this.porcentaje != null && this.totalPesos != null){
			BigDecimal division = this.porcentaje.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP);
			setComision(division.multiply(totalPesos, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
		}
	}

	public BigDecimal getTotalPesos() {
		return totalPesos;
	}

	public void setTotalPesos(BigDecimal totalPesos) {
		this.totalPesos = totalPesos;
		
		if( this.porcentaje != null && this.totalPesos != null){
			BigDecimal division = this.porcentaje.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP);
			setComision(division.multiply(totalPesos, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
		}
		
		if( this.comision != null && this.totalPesos != null && this.totalComisionExtra != null){
			setGranTotal(this.totalPesos.add(this.comision).add(this.totalComisionExtra).setScale(2, RoundingMode.HALF_UP));
		}
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
		
		if( this.comision != null && this.totalPesos != null && this.totalComisionExtra != null){
			setGranTotal(this.totalPesos.add(this.comision).add(this.totalComisionExtra).setScale(2, RoundingMode.HALF_UP));
		}
	}

	public BigDecimal getComisionExtra() {
		return comisionExtra;
	}

	public void setComisionExtra(BigDecimal comisionExtra) {
		this.comisionExtra = comisionExtra;
		if(tc != null && comisionExtra != null){
			setTotalComisionExtra(this.tc.multiply(this.comisionExtra, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
		}
	}

	public BigDecimal getGranTotal() {
		return granTotal;
	}

	public void setGranTotal(BigDecimal granTotal) {
		this.granTotal = granTotal;
		
		if(this.granTotal != null && this.cantidad != null){
			setTcPonderado(granTotal.divide(this.cantidad, 8, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_UP));
		}
	}

	public BigDecimal getTcPonderado() {
		return tcPonderado;
	}

	public void setTcPonderado(BigDecimal tcPonderado) {
		this.tcPonderado = tcPonderado;
	}

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

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public BigDecimal getTotalComisionExtra() {
		return totalComisionExtra;
	}

	public void setTotalComisionExtra(BigDecimal totalComisionExtra) {
		this.totalComisionExtra = totalComisionExtra;
		if( this.comision != null && this.totalPesos != null && this.totalComisionExtra != null){
			setGranTotal(this.totalPesos.add(this.comision).add(this.totalComisionExtra));
		}
	}

	@Override
	public String toString() {
		return "Cotizacion [idCotizacion=" + idCotizacion + ", fecha=" + fecha
				+ ", idCliente=" + idCliente + ", idEmpresa=" + idEmpresa
				+ ", cantidad=" + cantidad + ", tc=" + tc + ", porcentaje="
				+ porcentaje + ", totalPesos=" + totalPesos + ", comision="
				+ comision + ", comisionExtra=" + comisionExtra
				+ ", totalComisionExtra=" + totalComisionExtra + ", granTotal="
				+ granTotal + ", tcPonderado=" + tcPonderado + "]";
	}

}
