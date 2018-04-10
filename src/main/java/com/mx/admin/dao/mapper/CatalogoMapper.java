package com.mx.admin.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.admin.bean.Catalogo;

public class CatalogoMapper implements RowMapper<Catalogo> {

	@Override
	public Catalogo mapRow(ResultSet row, int arg1) throws SQLException {
		Catalogo catalogo = new Catalogo();
		catalogo.setIdCatalogo(row.getInt(1));
		catalogo.setConcepto(row.getString(2));
		catalogo.setEmpresa(row.getString(3));
		catalogo.setPorcentaje(row.getBigDecimal(4));
		catalogo.setTipoClasificacion(row.getInt(5));
		catalogo.setFecha(row.getDate(6));
		return catalogo;
	}

}
