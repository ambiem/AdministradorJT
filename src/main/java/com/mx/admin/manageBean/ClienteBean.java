package com.mx.admin.manageBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mx.admin.bean.Cliente;
import com.mx.admin.dao.impl.ClienteDaoImpl;
import com.mx.admin.util.CommonUtils;

@ManagedBean(name = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String conceptoB;
	private String nombre;
	private List<Cliente> listaCliente;
	private Cliente cliente;
	private Cliente clienteEditar;

	@Autowired
	private ClienteDaoImpl clienteDao = new ClienteDaoImpl();

	@ManagedProperty(value = "#{commonUtils}")
	private CommonUtils util;

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	@PostConstruct
	public void init() {
		System.out.println("Iniciando consulta inicial clientes controller");
		listaCliente = clienteDao.consulta();
		cliente = new Cliente();
	}

	public void buscar() {
		System.out.println("Iniciando busqueda clientes controller");
		listaCliente = clienteDao.buscar(conceptoB, nombre);
		System.out.println("Fin busqueda clientes controller");
	}

	public void buscarTodo() {
		System.out.println("Buscando todas los clientes controller");
		listaCliente = clienteDao.consulta();
	}

	public void agregar() {
		System.out.println("Agregando nuevo cliente controller -> " );
		
		boolean resultado = clienteDao.agregar(cliente);
		
		System.out.println("Resultado agregar nuevo cliente controller -> " + resultado);
		
		cliente = new Cliente();
		listaCliente = clienteDao.consulta();
	}

	public void borrar(Cliente cliente) {
		System.out.println("Borrando cliente controller -> " + cliente.toString());
		
		boolean resultado = clienteDao.borrar(cliente);
		
		System.out.println("Resultado borrar cliente controller -> " + resultado);
		
		cliente = new Cliente();
		listaCliente = clienteDao.consulta();
	}

	public void editar(RowEditEvent event) {
		System.out.println("Editar cliente controller -> ");
		clienteEditar = (Cliente) event.getObject();
		
		System.out.println("Elemento a editar Cliente controller -> " + clienteEditar.toString());
		boolean resultado = clienteDao.editar(clienteEditar);
		
		System.out.println("Resultado editar cliente controller -> " + resultado);
		
		clienteEditar = new Cliente();
		listaCliente = clienteDao.consulta();
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

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteEditar() {
		return clienteEditar;
	}

	public void setClienteEditar(Cliente clienteEditar) {
		this.clienteEditar = clienteEditar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
