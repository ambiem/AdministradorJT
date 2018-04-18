package com.mx.admin.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mx.admin.bean.Cheque;
import com.mx.admin.dao.ChequesDao;
import com.mx.admin.dao.config.DaoConfig;
import com.mx.admin.dao.mapper.ChequesMapper;

public class ChequesDaoImpl implements ChequesDao {
	
	private static final String CONSULTA_INSERT = "INSERT INTO CHEQUE (CONCEPTO, MONTO, ID_CLIENTE, FECHA, ACTIVO) VALUES (:concepto,:monto,:idCliente,:fecha,1)";
	private static final String CONSULTA_UPDATE = "UPDATE CHEQUE SET CONCEPTO = :concepto, MONTO = :monto, ID_CLIENTE = :idCliente, FECHA = :fecha WHERE ID_CHEQUE = :idCheque";
	private static final String CONSULTA_SELECT_TODO = "SELECT CHEQUE.ID_CHEQUE, CHEQUE.CONCEPTO, CHEQUE.MONTO, CHEQUE.ID_CLIENTE, CLIENTE.NOMBRE, CHEQUE.FECHA FROM CHEQUE, CLIENTE WHERE CHEQUE.ACTIVO = 1 AND CLIENTE.ID_CLIENTE = CHEQUE.ID_CLIENTE ORDER BY CHEQUE.ID_CHEQUE DESC";
	private static final String CONSULTA_BUSQUEDA = "SELECT CHEQUE.ID_CHEQUE, CHEQUE.CONCEPTO, CHEQUE.MONTO, CHEQUE.ID_CLIENTE, CLIENTE.NOMBRE, CHEQUE.FECHA FROM CHEQUE, CLIENTE WHERE (CHEQUE.CONCEPTO LIKE :concepto OR CHEQUE.ID_CLIENTE = :idCliente OR CHEQUE.FECHA BETWEEN TO_DATE (:fechaInicio, 'dd/mm/yyyy') AND TO_DATE (:fechaFin, 'dd/mm/yyyy')) AND CHEQUE.ACTIVO = 1 AND CLIENTE.ID_CLIENTE = CHEQUE.ID_CLIENTE ORDER BY CHEQUE.ID_CHEQUE DESC";
	private static final String CONSULTA_DELETE = "UPDATE CHEQUE SET ACTIVO = 0 WHERE ID_CHEQUE = :idCheque";
	
	private static final Logger LOG = Logger.getLogger(ChequesDaoImpl.class);
	private DaoConfig daoConfig = new DaoConfig();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = daoConfig.getNameParameterJbdcTemplate();

	@Override
	public List<Cheque> consulta() {
		LOG.info("Consulta todos los cheques dao");
		List<Cheque> listaCheque = new ArrayList<Cheque>();
		try {
			listaCheque = namedParameterJdbcTemplate.query(CONSULTA_SELECT_TODO, new ChequesMapper());
		}catch(Exception e) {
			LOG.info("Fallo al consultar todos los cheques dao");
			e.printStackTrace();
			return listaCheque;
		}
		if(listaCheque != null ){
			LOG.info("Exito al consultar todos los cheques dao #elementos -> " + listaCheque.size());
		}
		return listaCheque;
	}

	@Override
	public boolean agregar(Cheque cheques) {
		LOG.info("Agregar nuevo cheque dao -> " + cheques.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("concepto",  cheques.getConcepto());
			namedParameters.put("monto",  cheques.getMonto());
			namedParameters.put("idCliente",  cheques.getIdCliente());
			namedParameters.put("fecha", new Date());
			
			namedParameterJdbcTemplate.update(CONSULTA_INSERT, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al agregar nuevo cheque dao -> " + cheques.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al agregar nuevo cheque dao -> " + cheques.toString());
		return true;
	}

	@Override
	public boolean editar(Cheque chequesEditar) {
		LOG.info("Editar Cheque dao -> " + chequesEditar.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("idCheque",  chequesEditar.getIdCheque());
			namedParameters.put("concepto",  chequesEditar.getConcepto());
			namedParameters.put("monto",  chequesEditar.getMonto());
			namedParameters.put("idCliente",  chequesEditar.getIdCliente());
			namedParameters.put("fecha",  chequesEditar.getFecha());
			
			namedParameterJdbcTemplate.update(CONSULTA_UPDATE, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al editar Cheque dao -> " + chequesEditar.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al editar Cheque dao -> " + chequesEditar.toString());
		return true;
	}

	@Override
	public boolean borrar(Cheque chequesB) {
		LOG.info("Borrar cheque dao -> " + chequesB.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCheque", chequesB.getIdCheque());
			namedParameterJdbcTemplate.update(CONSULTA_DELETE, namedParameters);
		} catch (Exception e) {
			LOG.info("Fallo al borrar cheque dao -> " + chequesB.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al borrar cheque dao -> " + chequesB.toString());
		return true;
	}

	@Override
	public List<Cheque> buscar(String conceptoB, Date fechaInicioB, Date fechaFinB, Integer clienteB) {
		List<Cheque> listaCheques = new ArrayList<Cheque>();
		LOG.info("Busqueda por parametros cheque dao -> concepto: "+ conceptoB + " fechaInicio: " + fechaInicioB + " fechaFin: " + fechaFinB + " idCliente: " + clienteB);
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("concepto", conceptoB);
			namedParameters.put("fechaInicio", fechaInicioB != null ? df.format(fechaInicioB) : fechaInicioB);
			namedParameters.put("fechaFin", fechaFinB != null ? df.format(fechaFinB): fechaFinB);
			namedParameters.put("idCliente", clienteB);
			
			listaCheques = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA, namedParameters, new ChequesMapper());
			
		}catch(Exception e) {
			LOG.info("Fallo busqueda por parametros cheque dao -> concepto: "+ conceptoB + " fechaInicio: " + fechaInicioB + " fechaFin: " + fechaFinB + " idCliente: " + clienteB);
			e.printStackTrace();
			return listaCheques;
		}
		if(listaCheques != null){
			LOG.info("Exito por parametros cheque dao -> concepto: "+ conceptoB + " fechaInicio: " + fechaInicioB + " fechaFin: " + fechaFinB + " idCliente: " + clienteB);
			LOG.info(" #elementos -> " + listaCheques.size());
		}
		
		return listaCheques;
	}

}