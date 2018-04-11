package com.mx.admin.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.mx.admin.bean.EntradaSalida;
import com.mx.admin.dao.EntradaSalidaDao;
import com.mx.admin.dao.config.DaoConfig;
import com.mx.admin.dao.mapper.EntradaSalidaMapper;

@Repository
@Qualifier("entradaSalidaDao")
public class EntradaSalidaDaoImpl implements EntradaSalidaDao {
	private static final String FECHA_V = "FECHA_V";
	private static final String TIPO_OPERACION_V = "TIPO_OPERACION_V";
	private static final String MONTO_V = "MONTO_V";
	private static final String CONCEPTO_V = "CONCEPTO_V";
	private static final String OPERACION_ID = "OPERACION_ID";
	private static final String CONSULTA_SELECT_TODO = "SELECT OPERACION.ID_OPERACION, OPERACION.CONCEPTO, OPERACION.MONTO, OPERACION.TIPO_OPERACION, OPERACION.FECHA, OPERACION.SALDO, OPERACION.ID_CLIENTE, CLIENTE.NOMBRE FROM OPERACION, CLIENTE WHERE OPERACION.ACTIVO = 1 AND CLIENTE.ID_CLIENTE = OPERACION.ID_CLIENTE ORDER BY ID_OPERACION DESC";
	private static final String CONSULTA_BUSQUEDA = "SELECT OPERACION.ID_OPERACION, OPERACION.CONCEPTO, OPERACION.MONTO, OPERACION.TIPO_OPERACION, OPERACION.FECHA, OPERACION.SALDO, OPERACION.ID_CLIENTE, CLIENTE.NOMBRE FROM OPERACION, CLIENTE WHERE (OPERACION.CONCEPTO LIKE :concepto OR OPERACION.TIPO_OPERACION = :tipoOperacion OR OPERACION.FECHA BETWEEN :fechaInicio AND :fechaFin OR OPERACION.ID_CLIENTE =:idCliente) AND OPERACION.ACTIVO = 1 AND CLIENTE.ID_CLIENTE = OPERACION.ID_CLIENTE ORDER BY ID_OPERACION DESC";
	private static final String CLIENTE_ID = "CLIENTE_V";
	
	private static final Logger LOG = Logger.getLogger(EntradaSalidaDaoImpl.class);

	private DaoConfig daoConfig = new DaoConfig();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = daoConfig.getNameParameterJbdcTemplate();

	@Autowired
	private SimpleJdbcCall simpleJdbcCall = daoConfig.getSimpleJdbcCall();
	
	@Autowired
	private SimpleJdbcCall simpleJdbcCallDelete = daoConfig.getSimpleJdbcCall();


	@Override
	public List<EntradaSalida> consulta() {
		LOG.info("Consulta todas las operaciones dao -> ");
		List<EntradaSalida> operaciones = new ArrayList<EntradaSalida>();
		try {
			operaciones = namedParameterJdbcTemplate.query(CONSULTA_SELECT_TODO, new EntradaSalidaMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return operaciones;
		}
		
		if(operaciones != null)
			LOG.info("Consulta todas las operaciones exitosa -> #Elementos " + operaciones.size());
		
		return operaciones;
	}

	@Override
	public Boolean agregar(EntradaSalida operacion) {
		LOG.info("Agregar nueva operacion dao -> " + operacion.toString());
		try {
			simpleJdbcCall.withSchemaName("administrador_jt").withCatalogName("INSERT_OPERACIONES_SALDOS").withProcedureName("actualizar_operacion_saldo").declareParameters(buildSqlParameters());
			
			MapSqlParameterSource in = new MapSqlParameterSource()
					.addValue(OPERACION_ID, operacion.getIdOperacion())
					.addValue(CONCEPTO_V, operacion.getConcepto())
					.addValue(MONTO_V, operacion.getMonto())
					.addValue(TIPO_OPERACION_V, operacion.getTipoOperacion())
					.addValue(CLIENTE_ID, operacion.getIdCliente())
					.addValue(FECHA_V, new Date());
			simpleJdbcCall.execute(in);
			
		} catch (Exception e) {
			LOG.info("Fallo al agregar nueva operacion dao -> ");
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al agregar nueva operacion dao -> " + operacion.toString());
		return true;
	}

	@Override
	public List<EntradaSalida> buscar(String conceptoB, Integer tipoMovimientoB, Date fechaInicioB, Date fechaFinB, Integer clienteB) {
		List<EntradaSalida> operaciones = new ArrayList<EntradaSalida>();
		LOG.info("Busqueda operacion dao -> concepto: " + conceptoB + " tipoMovimiento: " + tipoMovimientoB + " fechaInicio: " + fechaInicioB + " fechaFin: " + fechaFinB);
		
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("concepto", conceptoB);
			namedParameters.put("tipoOperacion", tipoMovimientoB);
			namedParameters.put("fechaInicio", fechaInicioB);
			namedParameters.put("fechaFin", fechaFinB);
			namedParameters.put("idCliente", clienteB);
			
			operaciones = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA,namedParameters, new EntradaSalidaMapper());
			
		} catch (Exception e) {
			LOG.info("Fallo busqueda operaciones dao");
			e.printStackTrace();
			return operaciones;
		}
		
		LOG.info("Resultado busqueda operacion dao exitoso #Elementos ->" + operaciones.size());
		return operaciones;
	}

	@Override
	public Boolean editar(EntradaSalida operacion) {
		LOG.info("Editar operacion dao -> " + operacion.toString());
		
		try {
			simpleJdbcCall.withSchemaName("administrador_jt").withCatalogName("INSERT_OPERACIONES_SALDOS").withProcedureName("actualizar_operacion_saldo").declareParameters(buildSqlParameters());
			MapSqlParameterSource in = new MapSqlParameterSource()
					.addValue(OPERACION_ID, operacion.getIdOperacion())
					.addValue(CONCEPTO_V, operacion.getConcepto())
					.addValue(MONTO_V, operacion.getMonto())
					.addValue(TIPO_OPERACION_V, operacion.getTipoOperacion())
					.addValue(CLIENTE_ID, operacion.getIdCliente())
					.addValue(FECHA_V, new Date());
			simpleJdbcCall.execute(in);
		} catch (Exception e) {
			LOG.info("Fallo al editar operacion dao -> ");
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al editar operacion dao -> " + operacion.toString());
		return true;
	}

	@Override
	public Boolean borrar(EntradaSalida operacion) {
		LOG.info("Eliminando operacion dao -> " + operacion.toString());

		try {
			simpleJdbcCallDelete.withSchemaName("administrador_jt").withCatalogName("DELETE_OPERACIONES_SALDO").withProcedureName("ACTUALIZAR_SALDO_BORRAR").declareParameters(buildSqlParametersDelete());
			
			MapSqlParameterSource in = new MapSqlParameterSource()
					.addValue(OPERACION_ID, operacion.getIdOperacion())
					.addValue(TIPO_OPERACION_V, operacion.getTipoOperacion())
					.addValue(MONTO_V, operacion.getMonto());
			simpleJdbcCallDelete.execute(in);
			
		} catch (Exception e) {
			LOG.info("Fallo al eliminar operacion dao -> ");
			e.printStackTrace();
			return false;
		}
		
		LOG.info("Exito al eliminar operacion dao -> " + operacion.toString());
		return true;
	}

	private SqlParameter[] buildSqlParameters() {
		return new SqlParameter[] {
				new SqlParameter(OPERACION_ID, Types.NUMERIC),
				new SqlParameter(CONCEPTO_V, Types.VARCHAR),
				new SqlParameter(MONTO_V, Types.NUMERIC),
				new SqlParameter(TIPO_OPERACION_V, Types.NUMERIC),
				new SqlParameter(FECHA_V, Types.DATE) };
	}
	
	private SqlParameter[] buildSqlParametersDelete() {
		return new SqlParameter[] {
				new SqlParameter(OPERACION_ID, Types.NUMERIC),
				new SqlParameter(MONTO_V, Types.NUMERIC),
				new SqlParameter(TIPO_OPERACION_V, Types.NUMERIC)};
	}

}
