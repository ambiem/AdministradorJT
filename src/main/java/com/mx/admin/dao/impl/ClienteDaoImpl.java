package com.mx.admin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mx.admin.bean.Cliente;
import com.mx.admin.dao.ClienteDao;
import com.mx.admin.dao.config.DaoConfig;
import com.mx.admin.dao.mapper.ClientesMapper;

public class ClienteDaoImpl implements ClienteDao{
	
	private static final String CONSULTA_INSERT = "INSERT INTO CLIENTE (NOMBRE, CONCEPTO, PORCENTAJE, FECHA, ACTIVO) VALUES (:nombre, :concepto, :porcentaje, :fecha, 1)";
	private static final String CONSULTA_UPDATE = "UPDATE CLIENTE SET CONCEPTO = :concepto, PORCENTAJE = :porcentaje, FECHA = :fecha, NOMBRE =:nombre WHERE ID_CLIENTE = :idCliente";
	private static final String CONSULTA_SELECT_TODO = "SELECT * FROM CLIENTE WHERE ACTIVO = 1 ORDER BY ID_CLIENTE DESC";
	private static final String CONSULTA_BUSQUEDA = "SELECT * FROM CLIENTE WHERE (CONCEPTO LIKE :concepto OR NOMBRE =:nombre) AND ACTIVO = 1 ORDER BY ID_CLIENTE DESC";
	private static final String CONSULTA_DELETE = "UPDATE CLIENTE SET ACTIVO = 0 WHERE ID_CLIENTE = :idCliente";
	private static final String CONSULTA_CLIENTE_ID = "SELECT * FROM CLIENTE WHERE ID_CLIENTE =:idCliente";
	
	private DaoConfig daoConfig = new DaoConfig();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = daoConfig.getNameParameterJbdcTemplate();


	@Override
	public List<Cliente> consulta() {
		System.out.println("Consulta todos los clientes dao");
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = namedParameterJdbcTemplate.query(CONSULTA_SELECT_TODO, new ClientesMapper());
		}catch(Exception e) {
			System.out.println("Fallo al consultar todos los clientes dao");
			e.printStackTrace();
			return clientes;
		}
		if(clientes != null ){
			System.out.println("Exito al consultar todos los clientes dao #elementos -> " + clientes.size());
		}
		return clientes;
	}

	@Override
	public List<Cliente> buscar(String conceptoB, String nombre) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		System.out.println("Busqueda por parametros cliente dao -> concepto: "+ conceptoB +" nombre: " + nombre);
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("concepto", conceptoB);
			namedParameters.put("nombre", nombre);
			
			clientes = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA, namedParameters, new ClientesMapper());
			
		}catch(Exception e) {
			System.out.println("Fallo busqueda por parametros cliente dao -> concepto: "+ conceptoB +" nombre: " + nombre);
			e.printStackTrace();
			return clientes;
		}
		if(clientes != null){
			System.out.println("Exito busqueda por parametros cliente dao -> concepto: "+ conceptoB +" nombre: " + nombre);
			System.out.println(" #elementos -> " + clientes.size());
		}
		
		return clientes;
	}

	@Override
	public boolean agregar(Cliente cliente) {
		System.out.println("Agregar nuevo cliente dao -> " + cliente.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("nombre",  cliente.getNombre());
			namedParameters.put("concepto",  cliente.getConcepto());
			namedParameters.put("porcentaje",  cliente.getPorcentaje());
			namedParameters.put("fecha", new Date());
			
			namedParameterJdbcTemplate.update(CONSULTA_INSERT, namedParameters);
		}catch(Exception e) {
			System.out.println("Fallo al agregar nuevo cliente dao -> " + cliente.toString());
			e.printStackTrace();
			return false;
		}
		System.out.println("Exito al agregar nuevo cliente dao -> " + cliente.toString());
		return true;
	}

	@Override
	public boolean borrar(Cliente cliente) {
		System.out.println("Borrar cliente dao -> " + cliente.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCliente", cliente.getIdCliente());
			namedParameterJdbcTemplate.update(CONSULTA_DELETE, namedParameters);
		} catch (Exception e) {
			System.out.println("Fallo al borrar cliente dao -> " + cliente.toString());
			e.printStackTrace();
			return false;
		}
		System.out.println("Exito al borrar cliente dao -> " + cliente.toString());
		return true;
	}

	@Override
	public boolean editar(Cliente clienteEditar) {
		System.out.println("Agregar nuevo cliente dao -> " + clienteEditar.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			
			namedParameters.put("idCliente",  clienteEditar.getIdCliente());
			namedParameters.put("nombre",  clienteEditar.getNombre());
			namedParameters.put("concepto",  clienteEditar.getConcepto());
			namedParameters.put("porcentaje",  clienteEditar.getPorcentaje());
			namedParameters.put("fecha", clienteEditar.getFecha());
			
			namedParameterJdbcTemplate.update(CONSULTA_UPDATE, namedParameters);
		}catch(Exception e) {
			System.out.println("Fallo al agregar nuevo cliente dao -> " + clienteEditar.toString());
			e.printStackTrace();
			return false;
		}
		System.out.println("Exito al agregar nuevo cliente dao -> " + clienteEditar.toString());
		return true;
	}

	@Override
	public Cliente obtenerCliente(Integer idCliente) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente clienteResultado = new Cliente();
		
		System.out.println("Busqueda de cliente dao -> idCliente: "+ idCliente );
		
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCliente", idCliente);
			clientes = namedParameterJdbcTemplate.query(CONSULTA_CLIENTE_ID, namedParameters, new ClientesMapper());
		}catch(Exception e) {
			System.out.println("Fallo busqueda de cliente dao -> idCliente: "+ idCliente );
			e.printStackTrace();
			return clienteResultado;
		}
		if(clientes != null){
			clienteResultado = clientes.get(0);
			System.out.println("Exito busqueda de cliente dao -> idCliente: "+ idCliente );
			System.out.println(" #Cliente -> " + clienteResultado.toString());
		}
		
		return clienteResultado;
	}

}
