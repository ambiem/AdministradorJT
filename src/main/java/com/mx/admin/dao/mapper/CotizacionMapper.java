package com.mx.admin.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.admin.bean.Cotizacion;

public class CotizacionMapper implements RowMapper<Cotizacion> {

	@Override
	public Cotizacion mapRow(ResultSet row, int arg1) throws SQLException {
		Cotizacion cotizacion = new Cotizacion();
		cotizacion.setIdCotizacion(row.getInt(1));
		cotizacion.setCantidad(row.getBigDecimal(2));
		cotizacion.setTc(row.getBigDecimal(3));
		cotizacion.setPorcentaje(row.getBigDecimal(4));
		cotizacion.setTotalPesos(row.getBigDecimal(5));
		cotizacion.setComision(row.getBigDecimal(6));
		cotizacion.setComisionExtra(row.getBigDecimal(7));
		cotizacion.setGranTotal(row.getBigDecimal(8));
		cotizacion.setTcPonderado(row.getBigDecimal(9));
		cotizacion.setFecha(row.getDate(10));
		cotizacion.setIdCliente(row.getInt(11));
		cotizacion.setIdEmpresa(row.getInt(12));
		cotizacion.setTotalComisionExtra(row.getBigDecimal(13));
		cotizacion.setNombreCliente(row.getString(14));
		cotizacion.setNombreEmpresa(row.getString(15));
		return cotizacion;
	}
}
