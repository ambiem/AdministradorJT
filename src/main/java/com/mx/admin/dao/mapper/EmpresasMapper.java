package com.mx.admin.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.admin.bean.Empresa;

public class EmpresasMapper implements RowMapper<Empresa> {

	@Override
	public Empresa mapRow(ResultSet row, int arg1) throws SQLException {
		Empresa empresa = new Empresa();
		
		
		empresa.setIdEmpresa(row.getInt(1));
		empresa.setNombre(row.getString(2));
		empresa.setIdCliente(row.getInt(3));
		empresa.setFecha(row.getDate(4));
		empresa.setNombreCliente(row.getString(5));
		
		return empresa;
	}

}
