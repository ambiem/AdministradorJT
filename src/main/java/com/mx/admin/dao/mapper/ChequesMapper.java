package com.mx.admin.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mx.admin.bean.Cheque;

import org.springframework.jdbc.core.RowMapper;

public class ChequesMapper implements RowMapper<Cheque> {

	@Override
	public Cheque mapRow(ResultSet row, int arg1) throws SQLException {
		Cheque cheque = new Cheque();
		cheque.setIdCheque(row.getInt(1));
		cheque.setConcepto(row.getString(2));
		cheque.setMonto(row.getBigDecimal(3));
		cheque.setIdCliente(row.getInt(4));
		cheque.setNombreCliente(row.getString(5));
		cheque.setFecha(row.getDate(6));
		return cheque;
	}

}
