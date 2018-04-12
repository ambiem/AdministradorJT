package com.mx.admin.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.mx.admin.bean.EntradaSalida;
import com.mx.admin.bean.MovimientoCte;

public class MovimientosCteMapper implements  RowMapper<MovimientoCte> {

	@Override
	public MovimientoCte mapRow(ResultSet row, int arg1) throws SQLException {
		MovimientoCte movimiento = new MovimientoCte();
		movimiento.setIdMovimientoCte(row.getInt(1));
		movimiento.setConcepto(row.getString(2));
		movimiento.setMonto(row.getBigDecimal(3));
		movimiento.setTipoMovimiento(row.getInt(4));
		movimiento.setFecha(row.getDate(5));
		movimiento.setIdCliente(row.getInt(6));
		movimiento.setNombreCliente(row.getString(7));
		return movimiento;
	}

}
