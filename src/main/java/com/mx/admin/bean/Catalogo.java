package com.mx.admin.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Catalogo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idCatalogo;
	private Date fecha;
	private String concepto;
	private Integer tipoClasificacion;
	private String empresa;
	private BigDecimal porcentaje;

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Integer getTipoClasificacion() {
		return tipoClasificacion;
	}

	public void setTipoClasificacion(Integer tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	@Override
	public String toString() {
		return "Catalogo [idCatalogo=" + idCatalogo + ", fecha=" + fecha
				+ ", concepto=" + concepto + ", tipoClasificacion="
				+ tipoClasificacion + ", empresa=" + empresa + ", porcentaje="
				+ porcentaje + "]";
	}

}
