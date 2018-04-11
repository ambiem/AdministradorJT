package com.mx.admin.manageBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
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
	private static final Logger LOG = Logger.getLogger(CatalogoBean.class);

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
		LOG.info("Iniciando consulta inicial catalogos controller");
		listaCatalogo = catalogoDao.consulta();
		catalogo = new Catalogo();
	}

	public void buscar() {
		LOG.info("Iniciando busqueda catalogos controller");
		listaCatalogo = catalogoDao.buscar(conceptoB, clasificacionB, empresaB);
		LOG.info("Fin busqueda catalogos controller");
	}

	public void buscarTodo() {
		LOG.info("Buscando todas los catalogos controller");
		listaCatalogo = catalogoDao.consulta();
	}

	public void agregar() {
		LOG.info("Agregando nuevo catalogo controller -> " );
		
		boolean resultado = catalogoDao.agregar(catalogo);
		
		LOG.info("Resultado agregar nuevo catalogo controller -> " + resultado);
		
		catalogo = new Catalogo();
		listaCatalogo = catalogoDao.consulta();
	}

	public void borrar(Catalogo catalogoB) {
		LOG.info("Borrando catalogo controller -> " + catalogoB.toString());
		
		boolean resultado = catalogoDao.borrar(catalogoB);
		
		LOG.info("Resultado borrar catalogo controller -> " + resultado);
		
		catalogo = new Catalogo();
		listaCatalogo = catalogoDao.consulta();
	}

	public void editar(RowEditEvent event) {
		LOG.info("Editar catalogo controller -> ");
		catalogoEditar = (Catalogo) event.getObject();
		
		LOG.info("Elemento a editar catalogo controller -> " + catalogoEditar.toString());
		boolean resultado = catalogoDao.editar(catalogoEditar);
		
		LOG.info("Resultado editar catalogo controller -> " + resultado);
		
		catalogoEditar = new Catalogo();
		listaCatalogo = catalogoDao.consulta();
	}
	
	public void cancelarEdicion(RowEditEvent event) {
    	LOG.info("Cancelar Editar fila");
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
