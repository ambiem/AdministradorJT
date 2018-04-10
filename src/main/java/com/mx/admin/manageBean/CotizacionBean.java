package com.mx.admin.manageBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mx.admin.bean.Cliente;
import com.mx.admin.bean.Cotizacion;
import com.mx.admin.bean.Empresa;
import com.mx.admin.dao.impl.ClienteDaoImpl;
import com.mx.admin.dao.impl.CotizacionDaoImpl;
import com.mx.admin.dao.impl.EmpresaDaoImpl;
import com.mx.admin.util.CommonUtils;
import com.mx.admin.viewBean.CotizacionView;

@ManagedBean(name = "cotizacionBean")
@SessionScoped
public class CotizacionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date fechaInicioB;
	private Date fechaFinB;
	private Integer clienteB;
	private Integer empresaB;
	private List<Cotizacion> listaCotizaciones;
	private CotizacionView cotizacionView;
	private Cotizacion cotizacionEditar;
	private List<Empresa> listaEmpresas;
	private List<Empresa> listaEmpresasTabla;

	@Autowired
	private CotizacionDaoImpl cotizacionDao = new CotizacionDaoImpl();
	
	@Autowired
	private EmpresaDaoImpl empresaDao = new EmpresaDaoImpl();
	
	@Autowired
	private ClienteDaoImpl clienteDao = new ClienteDaoImpl();

	@ManagedProperty(value = "#{commonUtils}")
	private CommonUtils util;

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	@PostConstruct
	public void init() {
		System.out.println("Iniciando consulta inicial cotizaciones controller");
		listaCotizaciones = cotizacionDao.consulta();
		cotizacionView = new CotizacionView();
	}

	public void buscar() {
		System.out.println("Iniciando busqueda cotizaciones controller");
		listaCotizaciones = cotizacionDao.buscar(clienteB, empresaB,fechaInicioB, fechaFinB);
		System.out.println("Fin busqueda cotizaciones controller");
	}

	public void agregar() {
		System.out.println("Agregando nueva cotizacion controller -> " );
		boolean resultado = cotizacionDao.agregar(cotizacionView);
		
		System.out.println("Resultado agregar nueva cotizacion controller -> " + resultado);
		
		cotizacionView = new CotizacionView();
		listaCotizaciones = cotizacionDao.consulta();
		listaEmpresas = new ArrayList<Empresa>();
	}
	
	public void buscarTodo() {
		System.out.println("Buscando todas las cotizaciones controller");
		listaCotizaciones = cotizacionDao.consulta();
	}
	
	public void borrar(Cotizacion cotizacionB) {
		System.out.println("Borrando cotizacion controller -> " + cotizacionB.toString());
		
		boolean resultado = cotizacionDao.borrar(cotizacionB);
		
		System.out.println("Resultado borrar cotizacion controller -> " + resultado);
		
		cotizacionView = new CotizacionView();
		listaCotizaciones = cotizacionDao.consulta();
	}

	public void cancelarEdicion(RowEditEvent event) {
    	System.out.println("Cancelar Editar fila");
    }

	public void editar(RowEditEvent event) {
		System.out.println("Editar cotizacion controller -> ");
		cotizacionEditar =  (Cotizacion) event.getObject();
		
		System.out.println("Elemento a editar cotizacion controller -> " + cotizacionEditar.toString());
		
		Cliente cliente = clienteDao.obtenerCliente(cotizacionEditar.getIdCliente());
		
		cotizacionEditar.setPorcentaje(cliente.getPorcentaje());
		
		cotizacionEditar.setComision(cotizacionEditar.getTotalPesos().multiply(cotizacionEditar.getPorcentaje().divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP));
		cotizacionEditar.setTotalComisionExtra(cotizacionEditar.getTc().multiply(cotizacionEditar.getComisionExtra()).setScale(2, RoundingMode.HALF_UP));
		cotizacionEditar.setGranTotal(cotizacionEditar.getTotalPesos().add(cotizacionEditar.getComision()).add(cotizacionEditar.getTotalComisionExtra()).setScale(2, RoundingMode.HALF_UP));
		cotizacionEditar.setTotalPesos(cotizacionEditar.getCantidad().multiply(cotizacionEditar.getTc()).setScale(2, RoundingMode.HALF_UP));
		cotizacionEditar.setTcPonderado(cotizacionEditar.getGranTotal().divide(cotizacionEditar.getCantidad(), 8, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP));
		
		boolean resultado = cotizacionDao.editar(cotizacionEditar);
		
		System.out.println("Resultado editar cotizacion controller -> " + resultado);
		
		cotizacionEditar = new Cotizacion();
		listaCotizaciones = cotizacionDao.consulta();
		listaEmpresasTabla = new ArrayList<Empresa>();
	}
	
	public void cancelarAgregar(){
		cotizacionView = new CotizacionView();
	}
	
	public void actualizarEmpresas(AjaxBehaviorEvent event){
		listaEmpresas = empresaDao.consultaEmpresasCliente(cotizacionView.getIdCliente());
		Cliente cliente = clienteDao.obtenerCliente(cotizacionView.getIdCliente());
		cotizacionView.setPorcentaje(cliente.getPorcentaje());
	}
	
	public void actualizarEmpresasTabla(AjaxBehaviorEvent event){
		Integer idClienteE =  (Integer) ((UIOutput)event.getSource()).getValue();
		listaEmpresasTabla = empresaDao.consultaEmpresasCliente(idClienteE);
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

	public Integer getEmpresaB() {
		return empresaB;
	}

	public void setEmpresaB(Integer empresaB) {
		this.empresaB = empresaB;
	}

	public List<Cotizacion> getListaCotizaciones() {
		return listaCotizaciones;
	}

	public void setListaCotizaciones(List<Cotizacion> listaCotizaciones) {
		this.listaCotizaciones = listaCotizaciones;
	}

	public CotizacionView getCotizacion() {
		return cotizacionView;
	}

	public void setCotizacion(CotizacionView cotizacion) {
		this.cotizacionView = cotizacion;
	}

	public Cotizacion getCotizacionEditar() {
		return cotizacionEditar;
	}

	public void setCotizacionEditar(Cotizacion cotizacionEditar) {
		this.cotizacionEditar = cotizacionEditar;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public List<Empresa> getListaEmpresasTabla() {
		return listaEmpresasTabla;
	}

	public void setListaEmpresasTabla(List<Empresa> listaEmpresasTabla) {
		this.listaEmpresasTabla = listaEmpresasTabla;
	}

}
