package com.mx.admin.manageBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mx.admin.bean.MovimientoCte;
import com.mx.admin.dao.impl.MovimientoCteDaoImpl;
import com.mx.admin.util.CommonUtils;

@ManagedBean(name = "movimientoCteBean")
@SessionScoped
public class MovimientoCteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MovimientoCteBean.class);
	
	private List<MovimientoCte> listaMovimientoCte;
	private MovimientoCte movimientoCte = new MovimientoCte();
	private MovimientoCte movimientoCteEditar = new MovimientoCte();
	private String conceptoB;
	private Integer tipoMovimientoB;
	private Date fechaInicioB;
	private Date fechaFinB;
	private Integer clienteB;

	@Autowired
	private MovimientoCteDaoImpl movimientoCteDao = new MovimientoCteDaoImpl();

	@ManagedProperty(value = "#{commonUtils}")
	private CommonUtils util;

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	@PostConstruct
	public void init() {
		LOG.info("Iniciando consulta inicial operaciones controller");
		listaMovimientoCte = movimientoCteDao.consulta();
		movimientoCte = new MovimientoCte();
		movimientoCteEditar = new MovimientoCte();
	}

	public void agregar() {
		LOG.info("Agregando nueva Operacion controller");
		boolean resultado = movimientoCteDao.agregar(movimientoCte);
		
		LOG.info("Resultado agregar Operacion -> " + resultado);
		
		movimientoCte = new MovimientoCte();
		listaMovimientoCte = movimientoCteDao.consulta();
	}
	
	public void editar(RowEditEvent event) {
		LOG.info("Editando operacion controller");
		movimientoCteEditar = (MovimientoCte) event.getObject();

		LOG.info("Elemento a editar controller -> " + movimientoCteEditar.toString());
		
		boolean resultado = movimientoCteDao.editar(movimientoCteEditar);
		LOG.info("Resultado editar operacion controller -> " + resultado);
		
		movimientoCteEditar = new MovimientoCte();
		listaMovimientoCte = movimientoCteDao.consulta();
		
	}
	
	public void cancelarEdicion(RowEditEvent event) {
    	LOG.info("Cancelar Editar fila");
    }
	
	public void borrar(MovimientoCte movimientoCteB) {
		LOG.info("Borrando operacion controller -> " + movimientoCteB.toString());
		
		boolean resultado = movimientoCteDao.borrar(movimientoCteB);
		
		LOG.info("Resultado borrar operacion controller -> " + resultado);
		movimientoCteEditar = new MovimientoCte();
		listaMovimientoCte = movimientoCteDao.consulta();
	}

	public void buscar() {
		LOG.info("Busqueda operacion por parametros controller");
		listaMovimientoCte = movimientoCteDao.buscar(conceptoB, tipoMovimientoB, fechaInicioB, fechaFinB, clienteB);
		LOG.info("Fin Busqueda operacion por parametros controller");
	}
	
	public void buscarTodo() {
		LOG.info("Buscando todas las operaciones controller");
		listaMovimientoCte = movimientoCteDao.consulta();
	}

	public List<MovimientoCte> getListaMovimientoCte() {
		return listaMovimientoCte;
	}

	public void setListaMovimientoCte(List<MovimientoCte> listaMovimientoCte) {
		this.listaMovimientoCte = listaMovimientoCte;
	}

	public MovimientoCte getMovimientoCte() {
		return movimientoCte;
	}

	public void setMovimientoCte(MovimientoCte movimientoCte) {
		this.movimientoCte = movimientoCte;
	}

	public MovimientoCte getMovimientoCteEditar() {
		return movimientoCteEditar;
	}

	public void setMovimientoCteEditar(MovimientoCte movimientoCteEditar) {
		this.movimientoCteEditar = movimientoCteEditar;
	}

	public String getConceptoB() {
		return conceptoB;
	}

	public void setConceptoB(String conceptoB) {
		this.conceptoB = conceptoB;
	}

	public Integer getTipoMovimientoB() {
		return tipoMovimientoB;
	}

	public void setTipoMovimientoB(Integer tipoMovimientoB) {
		this.tipoMovimientoB = tipoMovimientoB;
	}

	public Date getFechaInicioB() {
		return fechaInicioB;
	}

	public void setFechaInicioB(Date fechaInicioB) {
		this.fechaInicioB = fechaInicioB;
	}

	public Date getFechaFinB() {
		return fechaFinB;
	}

	public void setFechaFinB(Date fechaFinB) {
		this.fechaFinB = fechaFinB;
	}

	public Integer getClienteB() {
		return clienteB;
	}

	public void setClienteB(Integer clienteB) {
		this.clienteB = clienteB;
	}
	
	
}