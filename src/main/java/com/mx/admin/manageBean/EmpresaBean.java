package com.mx.admin.manageBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
		System.out.println("Iniciando consulta inicial empresas controller");
		listaEmpresas = empresaDao.consulta();
		empresa = new Empresa();
	}

	public void buscar() {
		System.out.println("Iniciando busqueda empresas controller");
		listaEmpresas = empresaDao.buscar(nombreB, clienteB);
		System.out.println("Fin busqueda empresas controller");
	}

	public void buscarTodo() {
		System.out.println("Buscando todas los empresas controller");
		listaEmpresas = empresaDao.consulta();
	}

	public void agregar() {
		System.out.println("Agregando nuevo empresa controller -> " );
		
		boolean resultado = empresaDao.agregar(empresa);
		
		System.out.println("Resultado agregar nuevo empresa controller -> " + resultado);
		
		empresa = new Empresa();
		listaEmpresas = empresaDao.consulta();
		
	}

	public void borrar(Empresa empresa) {
		System.out.println("Borrando empresa controller -> " + empresa.toString());
		
		boolean resultado = empresaDao.borrar(empresa);
		
		System.out.println("Resultado borrar empresa controller -> " + resultado);
		
		empresa = new Empresa();
		listaEmpresas = empresaDao.consulta();
	}

	public void editar(RowEditEvent event) {
		System.out.println("Editar empresa controller -> ");
		empresaEditar = (Empresa) event.getObject();
		
		System.out.println("Elemento a editar Empresa controller -> " + empresaEditar.toString());
		boolean resultado = empresaDao.editar(empresaEditar);
		
		System.out.println("Resultado editar empresa controller -> " + resultado);
		
		empresaEditar = new Empresa();
		listaEmpresas = empresaDao.consulta();
	}
	
	public void cancelarEdicion(RowEditEvent event) {
    	System.out.println("Cancelar Editar fila");
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
