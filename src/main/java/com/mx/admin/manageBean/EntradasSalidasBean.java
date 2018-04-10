package com.mx.admin.manageBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mx.admin.bean.EntradaSalida;
import com.mx.admin.dao.impl.EntradaSalidaDaoImpl;
import com.mx.admin.util.CommonUtils;

@ManagedBean(name = "entradasSalidasBean")
@SessionScoped
public class EntradasSalidasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<EntradaSalida> listaEntradaSalida;
	private EntradaSalida entradaSalida = new EntradaSalida();
	private EntradaSalida entradaSalidaEditar = new EntradaSalida();
	private String conceptoB;
	private Integer tipoMovimientoB;
	private Date fechaInicioB;
	private Date fechaFinB;
	private Integer clienteB;

	@Autowired
	private EntradaSalidaDaoImpl entradaSalidaDao = new EntradaSalidaDaoImpl();

	@ManagedProperty(value = "#{commonUtils}")
	private CommonUtils util;

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	@PostConstruct
	public void init() {
		System.out.println("Iniciando consulta inicial operaciones controller");
		listaEntradaSalida = entradaSalidaDao.consulta();
		entradaSalida = new EntradaSalida();
		entradaSalidaEditar = new EntradaSalida();
	}

	public void agregar() {
		System.out.println("Agregando nueva Operacion controller");
		boolean resultado = entradaSalidaDao.agregar(entradaSalida);
		
		System.out.println("Resultado agregar Operacion -> " + resultado);
		
		entradaSalida = new EntradaSalida();
		listaEntradaSalida = entradaSalidaDao.consulta();
	}
	
	public void editar(RowEditEvent event) {
		System.out.println("Editando operacion controller");
		entradaSalidaEditar = (EntradaSalida) event.getObject();

		System.out.println("Elemento a editar controller -> " + entradaSalidaEditar.toString());
		
		boolean resultado = entradaSalidaDao.editar(entradaSalidaEditar);
		System.out.println("Resultado editar operacion controller -> " + resultado);
		
		entradaSalidaEditar = new EntradaSalida();
		listaEntradaSalida = entradaSalidaDao.consulta();
		
	}
	
	public void cancelarEdicion(RowEditEvent event) {
    	System.out.println("Cancelar Editar fila");
    }
	
	public void borrar(EntradaSalida entradaSalidaB) {
		System.out.println("Borrando operacion controller -> " + entradaSalidaB.toString());
		
		boolean resultado = entradaSalidaDao.borrar(entradaSalidaB);
		
		System.out.println("Resultado borrar operacion controller -> " + resultado);
		entradaSalidaEditar = new EntradaSalida();
		listaEntradaSalida = entradaSalidaDao.consulta();
	}

	public void buscar() {
		System.out.println("Busqueda operacion por parametros controller");
		listaEntradaSalida = entradaSalidaDao.buscar(conceptoB, tipoMovimientoB, fechaInicioB, fechaFinB, clienteB);
		System.out.println("Fin Busqueda operacion por parametros controller");
	}
	
	public void buscarTodo() {
		System.out.println("Buscando todas las operaciones controller");
		listaEntradaSalida = entradaSalidaDao.consulta();
	}
	
	public List<EntradaSalida> getListaEntradaSalida() {
		return listaEntradaSalida;
	}

	public void setListaEntradaSalida(List<EntradaSalida> listaEntradaSalida) {
		this.listaEntradaSalida = listaEntradaSalida;
	}

	public EntradaSalida getEntradaSalida() {
		return entradaSalida;
	}

	public void setEntradaSalida(EntradaSalida entradaSalida) {
		this.entradaSalida = entradaSalida;
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

	public EntradaSalida getEntradaSalidaEditar() {
		return entradaSalidaEditar;
	}

	public void setEntradaSalidaEditar(EntradaSalida entradaSalidaEditar) {
		this.entradaSalidaEditar = entradaSalidaEditar;
	}

	public Integer getClienteB() {
		return clienteB;
	}

	public void setClienteB(Integer clienteB) {
		this.clienteB = clienteB;
	}

}
