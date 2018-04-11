package com.mx.admin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mx.admin.bean.Catalogo;
import com.mx.admin.dao.CatalogoDao;
import com.mx.admin.dao.config.DaoConfig;
import com.mx.admin.dao.mapper.CatalogoMapper;

public class CatalogoDaoImpl implements CatalogoDao{
	
	private static final String CONSULTA_INSERT = "INSERT INTO CATALOGO (CONCEPTO, ID_CLASIFICACION, EMPRESA, PORCENTAJE, FECHA) VALUES (:concepto,:tipoClasificacion,:empresa,:porcentaje,:fecha)";
	private static final String CONSULTA_UPDATE = "UPDATE CATALOGO SET CONCEPTO = :concepto, ID_CLASIFICACION = :tipoClasificacion, EMPRESA = :empresa, PORCENTAJE = :porcentaje, FECHA = :fecha WHERE ID_CATALOGO = :idCatalogo";
	private static final String CONSULTA_SELECT_TODO = "SELECT * FROM CATALOGO ORDER BY ID_CATALOGO DESC";
	private static final String CONSULTA_BUSQUEDA = "SELECT * FROM CATALOGO WHERE CONCEPTO LIKE :concepto OR ID_CLASIFICACION = :tipoClasificacion OR EMPRESA LIKE :empresa ORDER BY ID_CATALOGO DESC";
	private static final String CONSULTA_DELETE = "DELETE FROM CATALOGO WHERE ID_CATALOGO = :idCatalogo";
	
	private static final Logger LOG = Logger.getLogger(CatalogoDaoImpl.class);
	
	private DaoConfig daoConfig = new DaoConfig();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = daoConfig.getNameParameterJbdcTemplate();

	@Override
	public List<Catalogo> buscar(String conceptoB, Integer clasificacionB, String empresaB) {
		List<Catalogo> catalogos = new ArrayList<Catalogo>();
		LOG.info("Busqueda por parametros catalogo dao -> concepto: "+ conceptoB + " clasificacion: " + clasificacionB + " empresa: " + empresaB);
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("concepto", conceptoB);
			namedParameters.put("tipoClasificacion", clasificacionB);
			namedParameters.put("empresa", empresaB);
			
			catalogos = namedParameterJdbcTemplate.query(CONSULTA_BUSQUEDA, namedParameters, new CatalogoMapper());
			
		}catch(Exception e) {
			LOG.info("Fallo busqueda por parametros catalogo dao -> concepto: "+ conceptoB + " clasificacion: " + clasificacionB + " empresa: " + empresaB);
			e.printStackTrace();
			return catalogos;
		}
		if(catalogos != null){
			LOG.info("Exito busqueda por parametros catalogo dao -> concepto: "+ conceptoB + " clasificacion: " + clasificacionB + " empresa: " + empresaB);
			LOG.info(" #elementos -> " + catalogos.size());
		}
		
		return catalogos;
	}

	@Override
	public boolean agregar(Catalogo catalogo) {
		LOG.info("Agregar nuevo catalogo dao -> " + catalogo.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("concepto",  catalogo.getConcepto());
			namedParameters.put("empresa",  catalogo.getEmpresa());
			namedParameters.put("porcentaje",  catalogo.getPorcentaje());
			namedParameters.put("tipoClasificacion",  catalogo.getTipoClasificacion());
			namedParameters.put("fecha", new Date());
			
			namedParameterJdbcTemplate.update(CONSULTA_INSERT, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al agregar nuevo catalogo dao -> " + catalogo.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al agregar nuevo catalogo dao -> " + catalogo.toString());
		return true;
	}

	@Override
	public List<Catalogo> consulta() {
		LOG.info("Consulta todos los catalogos dao");
		List<Catalogo> catalogos = new ArrayList<Catalogo>();
		try {
			catalogos = namedParameterJdbcTemplate.query(CONSULTA_SELECT_TODO, new CatalogoMapper());
		}catch(Exception e) {
			LOG.info("Fallo al consultar todos los catalogos dao");
			e.printStackTrace();
			return catalogos;
		}
		if(catalogos != null ){
			LOG.info("Exito al consultar todos los catalogos dao #elementos -> " + catalogos.size());
		}
		return catalogos;
	}

	@Override
	public boolean borrar(Catalogo catalogoB) {
		LOG.info("Borrar catalogo dao -> " + catalogoB.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();
			namedParameters.put("idCatalogo", catalogoB.getIdCatalogo());
			namedParameterJdbcTemplate.update(CONSULTA_DELETE, namedParameters);
		} catch (Exception e) {
			LOG.info("Fallo al borrar catalogo dao -> " + catalogoB.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al borrar catalogo dao -> " + catalogoB.toString());
		return true;
	}

	public boolean editar(Catalogo catalogoEditar) {
		LOG.info("Editar catalogo dao -> " + catalogoEditar.toString());
		try {
			Map<String, Comparable> namedParameters = new HashMap();		
			namedParameters.put("idCatalogo",  catalogoEditar.getIdCatalogo());
			namedParameters.put("concepto",  catalogoEditar.getConcepto());
			namedParameters.put("empresa",  catalogoEditar.getEmpresa());
			namedParameters.put("porcentaje",  catalogoEditar.getPorcentaje());
			namedParameters.put("tipoClasificacion",  catalogoEditar.getTipoClasificacion());
			namedParameters.put("fecha",  catalogoEditar.getFecha());
			
			namedParameterJdbcTemplate.update(CONSULTA_UPDATE, namedParameters);
		}catch(Exception e) {
			LOG.info("Fallo al editar catalogo dao -> " + catalogoEditar.toString());
			e.printStackTrace();
			return false;
		}
		LOG.info("Exito al editar catalogo dao -> " + catalogoEditar.toString());
		return true;
	}
	
}
