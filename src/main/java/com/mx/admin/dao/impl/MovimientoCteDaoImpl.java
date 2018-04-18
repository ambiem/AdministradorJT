package com.mx.admin.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.mx.admin.bean.MovimientoCte;
import com.mx.admin.dao.MovimientoCteDao;
import com.mx.admin.dao.config.DaoConfig;
import com.mx.admin.dao.mapper.MovimientosCteMapper;

public class MovimientoCteDaoImpl implements MovimientoCteDao{
	
	private static final String CONSULTA_INSERT = "INSERT INTO MOVIMIENTO_CTE (CONCEPTO, MONTO, TIPO_MOVIMIENTO, ID_CLIENTE, FECHA, ACTIVO) values(:concepto, :monto, :tipoMovimiento, :idCliente, :fecha, 1)";
	private static final String CONSULTA_UPDATE = "UPDATE MOVIMIENTO_CTE SET CONCEPTO =:concepto, MONTO=:monto, TIPO_MOVIMIENTO=:tipoMovimiento, ID_CLIENTE=:idCliente, FECHA=:fecha WHERE ID_MOVIMIENTO =:idMovimiento";
	private static final String CONSULTA_SELECT_TODO = "SELECT MOVIMIENTO_CTE.ID_MOVIMIENTO, MOVIMIENTO_CTE.FECHA, MOVIMIENTO_CTE.CONCEPTO, MOVIMIENTO_CTE.MONTO, MOVIMIENTO_CTE.TIPO_MOVIMIENTO, MOVIMIENTO_CTE.ID_CLIENTE, CLIENTE.NOMBRE FROM MOVIMIENTO_CTE, CLIENTE WHERE MOVIMIENTO_CTE.ACTIVO = 1 AND CLIENTE.ID_CLIENTE = MOVIMIENTO_CTE.ID_CLIENTE ORDER BY MOVIMIENTO_CTE.ID_MOVIMIENTO DESC";
	private static final String CONSULTA_BUSQUEDA = "SELECT MOVIMIENTO_CTE.ID_MOVIMIENTO, MOVIMIENTO_CTE.FECHA, MOVIMIENTO_CTE.CONCEPTO, MOVIMIENTO_CTE.MONTO, MOVIMIENTO_CTE.TIPO_MOVIMIENTO, MOVIMIENTO_CTE.ID_CLIENTE, CLIENTE.NOMBRE FROM MOVIMIENTO_CTE, CLIENTE WHERE (MOVIMIENTO_CTE.CONCEPTO LIKE :concepto OR MOVIMIENTO_CTE.TIPO_MOVIMIENTO = :tipoMovimiento OR MOVIMIENTO_CTE.ID_CLIENTE =:idCliente OR MOVIMIENTO_CTE.FECHA BETWEEN TO_DATE(:fechaInicio, 'dd/mm/yyyy') AND TO_DATE(:fechaFin, 'dd/mm/yyyy')) AND  CLIENTE.ID_CLIENTE = MOVIMIENTO_CTE.ID_CLIENTE AND MOVIMIENTO_CTE.ACTIVO = 1 ORDER BY MOVIMIENTO_CTE.ID_MOVIMIENTO DESC";
	private static final String CONSULTA_DELETE = "UPDATE ACTIVO = 0 WHERE ID_MOVIMIENTO = :idMovimiento";
	
	private static final Logger LOG = Logger.getLogger(MovimientoCteDaoImpl.class);

	private DaoConfig daoConfig = new DaoConfig();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = daoConfig.getNameParameterJbdcTemplate();

	@Autowired
	private SimpleJdbcCall simpleJdbcCall = daoConfig.getSimpleJdbcCall();
	
	@Autowired
	private SimpleJdbcCall simpleJdbcCallDelete = daoConfig.getSimpleJdbcCall();

	
	@Override
	public List<MovimientoCte> consulta() {
		LOG.info("Consulta todas las movimientoCtees dao -> ");
		List<MovimientoCte> movimientos = new ArrayList<MovimientoCte>();
		try {
			movimientos = namedParameterJdbcTemplate.query(CONSULTA_SELECT_TODO, new MovimientosCteMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return movimientos;
		}
		
		if(movimientos != null)
			LOG.info("Consulta todas las movimientoCtees exitosa -> #Elementos " + movimientos.size());
		
		return movimientos;
	}
	@Override
	public List<MovimientoCte> buscar(String conceptoB, Integer tipoMovimientoB, Date fechaInicioB, Date fechaFinB, Integer clienteB) {
		List<MovimientoCte> movimientos = new ArrayList<MovimientoCte>();
		LOG.info("Busqueda movimientoCte dao -> concepto: " + conceptoB + " tipoMovimiento: " + tipoMovimientoB + " fechaInicio: " + fechaInicioB + " fechaFin: " + fechaFinB + " idCliente:" + clienteB);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("concepto", conceptoB);
			namedParameters.put("tipoMovimiento", tipoMovimientoB);
			namedParameters.put("fechaInicio", fechaInicioB != null ? df.format(fechaInicioB) : fechaInicioB);
			namedParameters.put("fechaFin", fechaFinB != null ? df.format(fechaFinB) : fechaFinB);
			namedParameters.put("idCliente", clienteB);
			
			movimientos = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA,namedParameters, new MovimientosCteMapper());
			
		} catch (Exception e) {
			LOG.info("Fallo busqueda movimientoCtees dao");
			e.printStackTrace();
			return movimientos;
		}
		
		LOG.info("Resultado busqueda movimientoCte dao exitoso #Elementos ->" + movimientos.size());
		return movimientos;
	}
	

	@Override
	public boolean agregar(MovimientoCte movimientoCte) {
		LOG.info("Agregar nuevo movimientoCte dao -> " + movimientoCte.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCliente",  movimientoCte.getIdCliente());
			namedParameters.put("monto",  movimientoCte.getMonto());
			namedParameters.put("tipoMovimiento",  movimientoCte.getTipoMovimiento());
			namedParameters.put("concepto",  movimientoCte.getConcepto());
			namedParameters.put("fecha", new Date());
			
			namedParameterJdbcTemplate.update(CONSULTA_INSERT, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al agregar nuevo movimientoCte dao -> " + movimientoCte.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al agregar nuevo movimientoCte dao -> " + movimientoCte.toString());
		return true;
	}

	@Override
	public boolean editar(MovimientoCte movimientoCte) {
		LOG.info("Agregar nuevo movimientoCte dao -> " + movimientoCte.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idMovimiento",  movimientoCte.getIdMovimientoCte());
			namedParameters.put("idCliente",  movimientoCte.getIdCliente());
			namedParameters.put("monto",  movimientoCte.getMonto());
			namedParameters.put("tipoMovimiento",  movimientoCte.getTipoMovimiento());
			namedParameters.put("concepto",  movimientoCte.getConcepto());
			namedParameters.put("fecha", movimientoCte.getFecha());
			
			namedParameterJdbcTemplate.update(CONSULTA_UPDATE, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al agregar nuevo movimientoCte dao -> " + movimientoCte.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al agregar nuevo movimientoCte dao -> " + movimientoCte.toString());
		return true;
	}

	@Override
	public boolean borrar(MovimientoCte movimientoCte) {
		LOG.info("Borrar movimientoCte dao -> " + movimientoCte.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idMovimiento", movimientoCte.getIdMovimientoCte());
			namedParameterJdbcTemplate.update(CONSULTA_DELETE, namedParameters);
		} catch (Exception e) {
			LOG.info("Fallo al borrar movimientoCte dao -> " + movimientoCte.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al borrar movimientoCte dao -> " + movimientoCte.toString());
		return true;
	}

}
