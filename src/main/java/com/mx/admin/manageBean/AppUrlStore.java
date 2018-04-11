package com.mx.admin.manageBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

@ManagedBean(name = "appUrlStore")
@ApplicationScoped
public class AppUrlStore implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AppUrlStore.class);

	private String baseUrl = null;
	private String entradasSalidasUrl = null;
	private String catalogos = null;
	private String operaciones = null;

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getEntradasSalidasUrl() {
		return entradasSalidasUrl;
	}
	
	public String getCatalogos(){
		return catalogos;
	}
	
	public String getOperaciones(){
		return operaciones;
	}
	
	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String baseUrl = externalContext.getInitParameter("BaseUrl");

		this.baseUrl = baseUrl;
		this.entradasSalidasUrl = baseUrl + "vistas/entradas.xhtml";
		this.catalogos = baseUrl + "vistas/catalogos.xhtml";
		this.operaciones = baseUrl + "vistas/cotizaciones.xhtml";
	}
}
