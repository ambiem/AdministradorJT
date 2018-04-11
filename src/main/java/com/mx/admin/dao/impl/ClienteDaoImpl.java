package com.mx.admin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
	
	private static final Logger LOG = Logger.getLogger(ClienteDaoImpl.class);
	
	private DaoConfig daoConfig = new DaoConfig();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = daoConfig.getNameParameterJbdcTemplate();


	@Override
	public List<Cliente> consulta() {
		LOG.info("Consulta todos los clientes dao");
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = namedParameterJdbcTemplate.query(CONSULTA_SELECT_TODO, new ClientesMapper());
		}catch(Exception e) {
			LOG.info("Fallo al consultar todos los clientes dao");
			e.printStackTrace();
			return clientes;
		}
		if(clientes != null ){
			LOG.info("Exito al consultar todos los clientes dao #elementos -> " + clientes.size());
		}
		return clientes;
	}

	@Override
	public List<Cliente> buscar(String conceptoB, String nombre) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		LOG.info("Busqueda por parametros cliente dao -> concepto: "+ conceptoB +" nombre: " + nombre);
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("concepto", conceptoB);
			namedParameters.put("nombre", nombre);
			
			clientes = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA, namedParameters, new ClientesMapper());
			
		}catch(Exception e) {
			LOG.info("Fallo busqueda por parametros cliente dao -> concepto: "+ conceptoB +" nombre: " + nombre);
			e.printStackTrace();
			return clientes;
		}
		if(clientes != null){
			LOG.info("Exito busqueda por parametros cliente dao -> concepto: "+ conceptoB +" nombre: " + nombre);
			LOG.info(" #elementos -> " + clientes.size());
		}
		
		return clientes;
	}

	@Override
	public boolean agregar(Cliente cliente) {
		LOG.info("Agregar nuevo cliente dao -> " + cliente.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("nombre",  cliente.getNombre());
			namedParameters.put("concepto",  cliente.getConcepto());
			namedParameters.put("porcentaje",  cliente.getPorcentaje());
			namedParameters.put("fecha", new Date());
			
			namedParameterJdbcTemplate.update(CONSULTA_INSERT, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al agregar nuevo cliente dao -> " + cliente.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al agregar nuevo cliente dao -> " + cliente.toString());
		return true;
	}

	@Override
	public boolean borrar(Cliente cliente) {
		LOG.info("Borrar cliente dao -> " + cliente.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCliente", cliente.getIdCliente());
			namedParameterJdbcTemplate.update(CONSULTA_DELETE, namedParameters);
		} catch (Exception e) {
			LOG.info("Fallo al borrar cliente dao -> " + cliente.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al borrar cliente dao -> " + cliente.toString());
		return true;
	}

	@Override
	public boolean editar(Cliente clienteEditar) {
		LOG.info("Agregar nuevo cliente dao -> " + clienteEditar.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			
			namedParameters.put("idCliente",  clienteEditar.getIdCliente());
			namedParameters.put("nombre",  clienteEditar.getNombre());
			namedParameters.put("concepto",  clienteEditar.getConcepto());
			namedParameters.put("porcentaje",  clienteEditar.getPorcentaje());
			namedParameters.put("fecha", clienteEditar.getFecha());
			
			namedParameterJdbcTemplate.update(CONSULTA_UPDATE, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al agregar nuevo cliente dao -> " + clienteEditar.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al agregar nuevo cliente dao -> " + clienteEditar.toString());
		return true;
	}

	@Override
	public Cliente obtenerCliente(Integer idCliente) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente clienteResultado = new Cliente();
		
		LOG.info("Busqueda de cliente dao -> idCliente: "+ idCliente );
		
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCliente", idCliente);
			clientes = namedParameterJdbcTemplate.query(CONSULTA_CLIENTE_ID, namedParameters, new ClientesMapper());
		}catch(Exception e) {
			LOG.info("Fallo busqueda de cliente dao -> idCliente: "+ idCliente );
			e.printStackTrace();
			return clienteResultado;
		}
		if(clientes != null){
			clienteResultado = clientes.get(0);
			LOG.info("Exito busqueda de cliente dao -> idCliente: "+ idCliente );
			LOG.info(" #Cliente -> " + clienteResultado.toString());
		}
		
		return clienteResultado;
	}

}
