package com.mx.admin.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.admin.bean.Cliente;

public class ClientesMapper implements RowMapper<Cliente> {

	@Override
	public Cliente mapRow(ResultSet row, int arg1) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(row.getInt(1));
		cliente.setNombre(row.getString(2));
		cliente.setConcepto(row.getString(6));
		cliente.setPorcentaje(row.getBigDecimal(7));
		cliente.setFecha(row.getDate(8));
		return cliente;
	}

}
