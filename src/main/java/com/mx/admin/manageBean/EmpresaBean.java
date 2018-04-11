package com.mx.admin.manageBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mx.admin.bean.Empresa;
import com.mx.admin.dao.impl.EmpresaDaoImpl;
import com.mx.admin.util.CommonUtils;

@ManagedBean(name = "empresaBean")
@SessionScoped
public class EmpresaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(EmpresaBean.class);

	private String nombreB;
	private Integer clienteB;
	private List<Empresa> listaEmpresas;
	private Empresa empresa;
	private Empresa empresaEditar;

	@Autowired
	private EmpresaDaoImpl empresaDao = new EmpresaDaoImpl();

	@ManagedProperty(value = "#{commonUtils}")
	private CommonUtils util;

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	@PostConstruct
	public void init() {
		LOG.info("Iniciando consulta inicial empresas controller");
		listaEmpresas = empresaDao.consulta();
		empresa = new Empresa();
	}

	public void buscar() {
		LOG.info("Iniciando busqueda empresas controller");
		listaEmpresas = empresaDao.buscar(nombreB, clienteB);
		LOG.info("Fin busqueda empresas controller");
	}

	public void buscarTodo() {
		LOG.info("Buscando todas los empresas controller");
		listaEmpresas = empresaDao.consulta();
	}

	public void agregar() {
		LOG.info("Agregando nuevo empresa controller -> " );
		
		boolean resultado = empresaDao.agregar(empresa);
		
		LOG.info("Resultado agregar nuevo empresa controller -> " + resultado);
		
		empresa = new Empresa();
		listaEmpresas = empresaDao.consulta();
		
	}

	public void borrar(Empresa empresa) {
		LOG.info("Borrando empresa controller -> " + empresa.toString());
		
		boolean resultado = empresaDao.borrar(empresa);
		
		LOG.info("Resultado borrar empresa controller -> " + resultado);
		
		empresa = new Empresa();
		listaEmpresas = empresaDao.consulta();
	}

	public void editar(RowEditEvent event) {
		LOG.info("Editar empresa controller -> ");
		empresaEditar = (Empresa) event.getObject();
		
		LOG.info("Elemento a editar Empresa controller -> " + empresaEditar.toString());
		boolean resultado = empresaDao.editar(empresaEditar);
		
		LOG.info("Resultado editar empresa controller -> " + resultado);
		
		empresaEditar = new Empresa();
		listaEmpresas = empresaDao.consulta();
	}
	
	public void cancelarEdicion(RowEditEvent event) {
    	LOG.info("Cancelar Editar fila");
    }

	public String getNombreB() {
		return nombreB;
	}

	public void setNombreB(String nombreB) {
		this.nombreB = nombreB;
	}

	public Integer getClienteB() {
		return clienteB;
	}

	public void setClienteB(Integer clienteB) {
		this.clienteB = clienteB;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getEmpresaEditar() {
		return empresaEditar;
	}

	public void setEmpresaEditar(Empresa empresaEditar) {
		this.empresaEditar = empresaEditar;
	}

}
