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

import com.mx.admin.bean.Cotizacion;
import com.mx.admin.dao.CotizacionDao;
import com.mx.admin.dao.config.DaoConfig;
import com.mx.admin.dao.mapper.CotizacionMapper;
import com.mx.admin.viewBean.CotizacionView;

public class CotizacionDaoImpl implements CotizacionDao{
	
	private static final String CONSULTA_INSERT = "INSERT INTO COTIZACION (CANTIDAD, TC, PORCENTAJE, TOTALPESOS, COMISION, COMISIONEXTRA, TOTAL, TCPONDERADO, ID_CLIENTE, ID_EMPRESA, FECHA, TOTALCOMISIONEXTRA) VALUES (:cantidad, :tc, :porcentaje, :totalPesos, :comision, :comisionExtra, :total, :tcPonderado, :cliente, :empresa, :fecha, :totalComisionExtra)";
	private static final String CONSULTA_UPDATE = "UPDATE COTIZACION SET CANTIDAD = :cantidad, TC = :tc, PORCENTAJE = :porcentaje, TOTALPESOS = :totalPesos, COMISION = :comision, COMISIONEXTRA = :comisionExtra, TOTAL = :total, TCPONDERADO = :tcPonderado, ID_CLIENTE = :cliente, ID_EMPRESA = :empresa, FECHA = :fecha, TOTALCOMISIONEXTRA = :totalComisionExtra  WHERE ID_COTIZACION = :idCotizacion";
	private static final String CONSULTA_SELECT_TODO = "SELECT COTIZACION.ID_COTIZACION, COTIZACION.CANTIDAD, COTIZACION.TC, COTIZACION.PORCENTAJE, COTIZACION.TOTALPESOS, COTIZACION.COMISION, COTIZACION.COMISIONEXTRA, COTIZACION.TOTAL, COTIZACION.TCPONDERADO, COTIZACION.FECHA, COTIZACION.ID_CLIENTE, COTIZACION.ID_EMPRESA, COTIZACION.TOTALCOMISIONEXTRA, CLIENTE.NOMBRE, EMPRESA.NOMBRE FROM COTIZACION, CLIENTE, EMPRESA WHERE COTIZACION.ID_CLIENTE = CLIENTE.ID_CLIENTE AND COTIZACION.ID_EMPRESA = EMPRESA.ID_EMPRESA ORDER BY COTIZACION.ID_COTIZACION DESC";
	private static final String CONSULTA_BUSQUEDA = "SELECT COTIZACION.ID_COTIZACION, COTIZACION.CANTIDAD, COTIZACION.TC, COTIZACION.PORCENTAJE, COTIZACION.TOTALPESOS, COTIZACION.COMISION, COTIZACION.COMISIONEXTRA, COTIZACION.TOTAL, COTIZACION.TCPONDERADO, COTIZACION.FECHA, COTIZACION.ID_CLIENTE, COTIZACION.ID_EMPRESA, COTIZACION.TOTALCOMISIONEXTRA, CLIENTE.NOMBRE, EMPRESA.NOMBRE FROM COTIZACION, EMPRESA, CLIENTE WHERE (COTIZACION.ID_EMPRESA = :empresa OR COTIZACION.ID_CLIENTE = :cliente OR COTIZACION.FECHA BETWEEN TO_DATE(:fechaInicio, 'dd/mm/yyyy') AND TO_DATE(:fechaFin, 'dd/mm/yyyy')) AND COTIZACION.ID_CLIENTE = CLIENTE.ID_CLIENTE AND COTIZACION.ID_EMPRESA = EMPRESA.ID_EMPRESA ORDER BY COTIZACION.ID_COTIZACION DESC";
	private static final String CONSULTA_DELETE = "DELETE FROM COTIZACION WHERE ID_COTIZACION = :idCotizacion";
	
	private static final Logger LOG = Logger.getLogger(CotizacionDaoImpl.class);
	
	private DaoConfig daoConfig = new DaoConfig();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = daoConfig.getNameParameterJbdcTemplate();

	@Override
	public List<Cotizacion> buscar(Integer clienteB, Integer empresaB, Date fechaInicioB, Date fechaFinB) {
		List<Cotizacion> listaCotizaciones = new ArrayList<Cotizacion>();
		LOG.info("Busqueda parametros cotizacion dao -> clienteB: " + clienteB + " empresa: " + empresaB + " fechaInicio: "+ fechaInicioB + " fechaFin: " + fechaFinB);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("empresa", empresaB);
			namedParameters.put("cliente", clienteB);
			namedParameters.put("fechaInicio", fechaInicioB != null ? df.format(fechaInicioB) : fechaInicioB);
			namedParameters.put("fechaFin", fechaFinB != null ? df.format(fechaFinB) : fechaInicioB);
			
			listaCotizaciones = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA, namedParameters, new CotizacionMapper());
			
		}catch(Exception e) {
			LOG.info("Fallo con busqueda parametros cotizacion dao -> clienteB: " + clienteB + " empresa: " + empresaB + " fechaInicio: "+ fechaInicioB + " fechaFin: " + fechaFinB );
			e.printStackTrace();
			return listaCotizaciones;
		}

		if(listaCotizaciones != null){
			LOG.info("Exito con busqueda parametros cotizacion dao -> clienteB: " + clienteB + " empresa: " + empresaB + " fechaInicio: "+ fechaInicioB + " fechaFin: " + fechaFinB);
			LOG.info(" #Elementos " + listaCotizaciones.size());
		}
		return listaCotizaciones;
	}

	@Override
	public boolean agregar(CotizacionView cotizacion) {
		LOG.info("Agregar nueva cotizacion dao -> " + cotizacion.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("cantidad",  cotizacion.getCantidad());
			namedParameters.put("tc",  cotizacion.getTc());
			namedParameters.put("porcentaje",  cotizacion.getPorcentaje());
			namedParameters.put("totalPesos",  cotizacion.getTotalPesos());
			namedParameters.put("comision",  cotizacion.getComision());
			namedParameters.put("comisionExtra",  cotizacion.getComisionExtra());
			namedParameters.put("total",  cotizacion.getGranTotal());
			namedParameters.put("tcPonderado",  cotizacion.getTcPonderado());
			namedParameters.put("fecha", new Date());
			namedParameters.put("cliente",  cotizacion.getIdCliente());
			namedParameters.put("empresa",  cotizacion.getIdEmpresa());
			namedParameters.put("totalComisionExtra",  cotizacion.getTotalComisionExtra());
			
			namedParameterJdbcTemplate.update(CONSULTA_INSERT, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al agregar nueva cotizacion dao -> " + cotizacion.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al agregar nueva cotizacion dao -> " + cotizacion.toString());
		return true;
	}

	@Override
	public List<Cotizacion> consulta() {
		LOG.info("Consulta todas las cotizaciones dao -> ");
		List<Cotizacion> listaCotizaciones = new ArrayList<Cotizacion>();
		try {
			listaCotizaciones = namedParameterJdbcTemplate.query(CONSULTA_SELECT_TODO, new CotizacionMapper());
		}catch(Exception e) {
			LOG.info("Fallo al consultar todas las cotizaciones dao -> ");
			e.printStackTrace();
			return listaCotizaciones;
		}
		if(listaCotizaciones != null)
		LOG.info("Exito al consultar todas las cotizaciones dao -> #Elmentos" + listaCotizaciones.size());
		
		return listaCotizaciones;
	}

	@Override
	public boolean borrar(Cotizacion cotizacionB) {
		LOG.info("Borrar cotizacion dao -> " + cotizacionB.toString());
		
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCotizacion", cotizacionB.getIdCotizacion());
			namedParameterJdbcTemplate.update(CONSULTA_DELETE, namedParameters);
		} catch (Exception e) {
			LOG.info("Fallo al borrar cotizacion dao -> " + cotizacionB.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al borrar cotizacion dao -> " + cotizacionB.toString());
		return true;
	}

	@Override
	public boolean editar(Cotizacion cotizacion) {
		LOG.info("Editar cotizacion dao -> " + cotizacion.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("cantidad",  cotizacion.getCantidad());
			namedParameters.put("tc",  cotizacion.getTc());
			namedParameters.put("porcentaje",  cotizacion.getPorcentaje());
			namedParameters.put("totalPesos",  cotizacion.getTotalPesos());
			namedParameters.put("comision",  cotizacion.getComision());
			namedParameters.put("comisionExtra",  cotizacion.getComisionExtra());
			namedParameters.put("total",  cotizacion.getGranTotal());
			namedParameters.put("tcPonderado",  cotizacion.getTcPonderado());
			namedParameters.put("fecha", new Date());
			namedParameters.put("cliente",  cotizacion.getIdCliente());
			namedParameters.put("empresa",  cotizacion.getIdEmpresa());
			namedParameters.put("totalComisionExtra",  cotizacion.getTotalComisionExtra());
			
			namedParameters.put("idCotizacion",  cotizacion.getIdCotizacion());
			
			namedParameterJdbcTemplate.update(CONSULTA_UPDATE, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al editar cotizacion dao -> " + cotizacion.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al editar cotizacion dao -> " + cotizacion.toString());
		return true;
	}

}
