package com.mx.admin.manageBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mx.admin.bean.Catalogo;
import com.mx.admin.bean.EntradaSalida;
import com.mx.admin.dao.impl.CatalogoDaoImpl;
import com.mx.admin.util.CommonUtils;

@ManagedBean(name = "catalogoBean")
@SessionScoped
public class CatalogoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String conceptoB;
	private Integer clasificacionB;
	private String empresaB;
	private List<Catalogo> listaCatalogo;
	private Catalogo catalogo;
	private Catalogo catalogoEditar;

	@Autowired
	private CatalogoDaoImpl catalogoDao = new CatalogoDaoImpl();

	@ManagedProperty(value = "#{commonUtils}")
	private CommonUtils util;

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	@PostConstruct
	public void init() {
		System.out.println("Iniciando consulta inicial catalogos controller");
		listaCatalogo = catalogoDao.consulta();
		catalogo = new Catalogo();
	}

	public void buscar() {
		System.out.println("Iniciando busqueda catalogos controller");
		listaCatalogo = catalogoDao.buscar(conceptoB, clasificacionB, empresaB);
		System.out.println("Fin busqueda catalogos controller");
	}

	public void buscarTodo() {
		System.out.println("Buscando todas los catalogos controller");
		listaCatalogo = catalogoDao.consulta();
	}

	public void agregar() {
		System.out.println("Agregando nuevo catalogo controller -> " );
		
		boolean resultado = catalogoDao.agregar(catalogo);
		
		System.out.println("Resultado agregar nuevo catalogo controller -> " + resultado);
		
		catalogo = new Catalogo();
		listaCatalogo = catalogoDao.consulta();
	}

	public void borrar(Catalogo catalogoB) {
		System.out.println("Borrando catalogo controller -> " + catalogoB.toString());
		
		boolean resultado = catalogoDao.borrar(catalogoB);
		
		System.out.println("Resultado borrar catalogo controller -> " + resultado);
		
		catalogo = new Catalogo();
		listaCatalogo = catalogoDao.consulta();
	}

	public void editar(RowEditEvent event) {
		System.out.println("Editar catalogo controller -> ");
		catalogoEditar = (Catalogo) event.getObject();
		
		System.out.println("Elemento a editar catalogo controller -> " + catalogoEditar.toString());
		boolean resultado = catalogoDao.editar(catalogoEditar);
		
		System.out.println("Resultado editar catalogo controller -> " + resultado);
		
		catalogoEditar = new Catalogo();
		listaCatalogo = catalogoDao.consulta();
	}
	
	public void cancelarEdicion(RowEditEvent event) {
    	System.out.println("Cancelar Editar fila");
    }

	public String getConceptoB() {
		return conceptoB;
	}

	public void setConceptoB(String conceptoB) {
		this.conceptoB = conceptoB;
	}

	public Integer getClasificacionB() {
		return clasificacionB;
	}

	public void setClasificacionB(Integer clasificacionB) {
		this.clasificacionB = clasificacionB;
	}

	public String getEmpresaB() {
		return empresaB;
	}

	public void setEmpresaB(String empresaB) {
		this.empresaB = empresaB;
	}

	public List<Catalogo> getListaCatalogo() {
		return listaCatalogo;
	}

	public void setListaCatalogo(List<Catalogo> listaCatalogo) {
		this.listaCatalogo = listaCatalogo;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Catalogo getCatalogoEditar() {
		return catalogoEditar;
	}

	public void setCatalogoEditar(Catalogo catalogoEditar) {
		this.catalogoEditar = catalogoEditar;
	}

}
