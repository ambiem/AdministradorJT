package com.mx.admin.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.admin.bean.EntradaSalida;

public class EntradaSalidaMapper implements RowMapper<EntradaSalida> {

	@Override
	public EntradaSalida mapRow(ResultSet row, int arg1) throws SQLException {
		EntradaSalida operacion = new EntradaSalida();
		operacion.setIdOperacion(row.getInt(1));
		operacion.setConcepto(row.getString(2));
		operacion.setMonto(row.getBigDecimal(3));
		operacion.setTipoOperacion(row.getInt(4));
		operacion.setFecha(row.getDate(5));
		operacion.setSaldo(row.getBigDecimal(6));
		operacion.setIdCliente(row.getInt(7));
		operacion.setNombreCliente(row.getString(8));
		return operacion;
	}



}
