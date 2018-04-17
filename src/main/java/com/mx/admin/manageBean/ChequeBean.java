package com.mx.admin.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mx.admin.bean.Cheque;
import com.mx.admin.dao.impl.ChequesDaoImpl;
import com.mx.admin.util.CommonUtils;

@ManagedBean(name = "chequeBean")
@SessionScoped
public class ChequeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ChequeBean.class);
	
	private String conceptoB;
	private Integer clienteB;
	private Date fechaInicioB;
	private Date fechaFinB;
	
	private List<Cheque> listaCheques = new ArrayList<Cheque>();
	private Cheque cheque = new Cheque();
	private Cheque chequesEditar = new Cheque(); 
	
	@Autowired
	private ChequesDaoImpl chequesDaoImpl = new ChequesDaoImpl();
	
	@ManagedProperty(value = "#{commonUtils}")
	private CommonUtils util;

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	@PostConstruct
	public void init() {
		LOG.info("Iniciando consulta inicial cheques controller");
		listaCheques = chequesDaoImpl.consulta();
		cheque = new Cheque();
		chequesEditar = new Cheque();
	}

	public void agregar() {
		LOG.info("Agregando nueva Cheque controller");
		boolean resultado = chequesDaoImpl.agregar(cheque);
		
		LOG.info("Resultado agregar Cheque -> " + resultado);
		
		cheque = new Cheque();
		listaCheques = chequesDaoImpl.consulta();
	}
	
	public void editar(RowEditEvent event) {
		LOG.info("Editando cheques controller");
		chequesEditar = (Cheque) event.getObject();

		LOG.info("Elemento a editar controller -> " + chequesEditar.toString());
		
		boolean resultado = chequesDaoImpl.editar(chequesEditar);
		LOG.info("Resultado editar cheques controller -> " + resultado);
		
		chequesEditar = new Cheque();
		listaCheques = chequesDaoImpl.consulta();
		
	}
	
	public void cancelarEdicion(RowEditEvent event) {
    	LOG.info("Cancelar Editar fila");
    }
	
	public void borrar(Cheque chequesB) {
		LOG.info("Borrando cheques controller -> " + chequesB.toString());
		
		boolean resultado = chequesDaoImpl.borrar(chequesB);
		
		LOG.info("Resultado borrar cheques controller -> " + resultado);
		chequesEditar = new Cheque();
		listaCheques = chequesDaoImpl.consulta();
	}

	public void buscar() {
		LOG.info("Busqueda cheques por parametros controller");
		listaCheques = chequesDaoImpl.buscar(conceptoB, fechaInicioB, fechaFinB, clienteB);
		LOG.info("Fin Busqueda cheques por parametros controller");
	}
	
	public void buscarTodo() {
		LOG.info("Buscando todas las chequeses controller");
		listaCheques = chequesDaoImpl.consulta();
	}

	public String getConceptoB() {
		return conceptoB;
	}

	public void setConceptoB(String conceptoB) {
		this.conceptoB = conceptoB;
	}

	public Integer getClienteB() {
		return clienteB;
	}

	public void setClienteB(Integer clienteB) {
		this.clienteB = clienteB;
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

	public List<Cheque> getListaCheques() {
		return listaCheques;
	}

	public void setListaCheques(List<Cheque> listaCheques) {
		this.listaCheques = listaCheques;
	}

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheques) {
		this.cheque = cheques;
	}

	public Cheque getChequeEditar() {
		return chequesEditar;
	}

	public void setChequeEditar(Cheque chequesEditar) {
		this.chequesEditar = chequesEditar;
	}

}
