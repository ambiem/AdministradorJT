package com.mx.admin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mx.admin.bean.Empresa;
import com.mx.admin.dao.EmpresaDao;
import com.mx.admin.dao.config.DaoConfig;
import com.mx.admin.dao.mapper.EmpresasMapper;

public class EmpresaDaoImpl implements EmpresaDao {
	
	private static final String CONSULTA_INSERT = "INSERT INTO EMPRESA (NOMBRE, ID_CLIENTE, FECHA, ACTIVO) VALUES (:nombre, :idCliente, :fecha, 1)";
	private static final String CONSULTA_UPDATE = "UPDATE EMPRESA SET NOMBRE = :nombre, ID_CLIENTE =:idCliente, FECHA = :fecha WHERE ID_EMPRESA = :idEmpresa";
	private static final String CONSULTA_SELECT_TODO = "SELECT EMPRESA.ID_EMPRESA, EMPRESA.NOMBRE, EMPRESA.ID_CLIENTE, EMPRESA.FECHA, CLIENTE.NOMBRE FROM EMPRESA, CLIENTE WHERE EMPRESA.ID_CLIENTE = CLIENTE.ID_CLIENTE AND EMPRESA.ACTIVO = 1 ORDER BY ID_EMPRESA DESC";
	private static final String CONSULTA_BUSQUEDA = "SELECT EMPRESA.ID_EMPRESA, EMPRESA.NOMBRE, EMPRESA.ID_CLIENTE, EMPRESA.FECHA, CLIENTE.NOMBRE FROM EMPRESA, CLIENTE WHERE (EMPRESA.NOMBRE =:nombre OR EMPRESA.ID_CLIENTE =:idCliente) AND CLIENTE.ID_CLIENTE =:idCliente AND EMPRESA.ACTIVO = 1 ORDER BY ID_EMPRESA DESC";
	private static final String CONSULTA_BUSQUEDA_CLIENTE = "SELECT EMPRESA.ID_EMPRESA, EMPRESA.NOMBRE, EMPRESA.ID_CLIENTE, EMPRESA.FECHA, CLIENTE.NOMBRE FROM EMPRESA, CLIENTE WHERE EMPRESA.ID_CLIENTE =:idCliente AND EMPRESA.ACTIVO = 1 AND CLIENTE.ID_CLIENTE =:idCliente ORDER BY EMPRESA.ID_EMPRESA DESC";
	private static final String CONSULTA_DELETE = "UPDATE EMPRESA SET ACTIVO = 0 WHERE ID_EMPRESA = :idEmpresa";
	
	private DaoConfig daoConfig = new DaoConfig();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = daoConfig.getNameParameterJbdcTemplate();

	@Override
	public List<Empresa> consulta() {
		System.out.println("Consulta todas los empresas dao");
		List<Empresa> empresas = new ArrayList<Empresa>();
		try {
			empresas = namedParameterJdbcTemplate.query(CONSULTA_SELECT_TODO, new EmpresasMapper());
		}catch(Exception e) {
			System.out.println("Fallo al consultar todas los empresas dao");
			e.printStackTrace();
			return empresas;
		}
		if(empresas != null ){
			System.out.println("Exito al consultar todas los empresas dao #elementos -> " + empresas.size());
		}
		return empresas;
	}

	@Override
	public List<Empresa> buscar(String nombreB, Integer empresaB) {
		List<Empresa> empresas = new ArrayList<Empresa>();
		System.out.println("Busqueda por parametros empresa dao -> nombre: " + nombreB + " empresa: " + empresaB);
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("nombre", nombreB);
			namedParameters.put("idCliente", empresaB);
			
			empresas = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA, namedParameters, new EmpresasMapper());
			
		}catch(Exception e) {
			System.out.println("Fallo busqueda por parametros empresa dao -> nombre: " + nombreB + " empresa: " + empresaB);
			e.printStackTrace();
			return empresas;
		}
		if(empresas != null){
			System.out.println("Exito busqueda por parametros empresa dao -> nombre: " + nombreB + " empresa: " + empresaB);
			System.out.println(" #elementos -> " + empresas.size());
		}
		
		return empresas;
	}
	
	@Override
	public List<Empresa> consultaEmpresasCliente(Integer idCliente) {
		System.out.println("Consulta todos las empresas dao");
		List<Empresa> empresas = new ArrayList<Empresa>();
		
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCliente", idCliente);
			
			empresas = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA_CLIENTE, namedParameters, new EmpresasMapper());
		}catch(Exception e) {
			System.out.println("Fallo al consultar empresas dao por id: " + idCliente);
			e.printStackTrace();
			return empresas;
		}
		if(empresas != null ){
			System.out.println("Exito al consultar empresas por idCliente dao #elementos -> " + empresas.size());
		}
		return empresas;
	}

	@Override
	public boolean agregar(Empresa empresa) {
		System.out.println("Agregar nueva empresa dao -> " + empresa.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("nombre",  empresa.getNombre());
			namedParameters.put("idCliente",  empresa.getIdCliente());
			namedParameters.put("fecha", new Date());
			
			namedParameterJdbcTemplate.update(CONSULTA_INSERT, namedParameters);
		}catch(Exception e) {
			System.out.println("Fallo al agregar nueva empresa dao -> " + empresa.toString());
			e.printStackTrace();
			return false;
		}
		System.out.println("Exito al agregar nueva empresa dao -> " + empresa.toString());
		return true;
	}

	@Override
	public boolean borrar(Empresa empresa) {
		System.out.println("Borrar empresa dao -> " + empresa.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idEmpresa", empresa.getIdEmpresa());
			namedParameterJdbcTemplate.update(CONSULTA_DELETE, namedParameters);
		} catch (Exception e) {
			System.out.println("Fallo al borrar empresa dao -> " + empresa.toString());
			e.printStackTrace();
			return false;
		}
		System.out.println("Exito al borrar empresa dao -> " + empresa.toString());
		return true;
	}

	@Override
	public boolean editar(Empresa empresaEditar) {
		System.out.println("Agregar nueva empresa dao -> " + empresaEditar.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("idEmpresa",  empresaEditar.getIdEmpresa());
			namedParameters.put("nombre",  empresaEditar.getNombre());
			namedParameters.put("idCliente",  empresaEditar.getIdCliente());
			namedParameters.put("fecha", empresaEditar.getFecha());
			
			namedParameterJdbcTemplate.update(CONSULTA_UPDATE, namedParameters);
		}catch(Exception e) {
			System.out.println("Fallo al agregar nueva empresa dao -> " + empresaEditar.toString());
			e.printStackTrace();
			return false;
		}
		System.out.println("Exito al agregar nueva empresa dao -> " + empresaEditar.toString());
		return true;
	}

}
