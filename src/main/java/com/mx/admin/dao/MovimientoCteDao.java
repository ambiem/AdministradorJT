package com.mx.admin.dao;

import java.util.Date;
import java.util.List;

import com.mx.admin.bean.MovimientoCte;

public interface MovimientoCteDao {

	List<MovimientoCte> consulta();

	boolean agregar(MovimientoCte movimientoCte);

	boolean editar(MovimientoCte movimientoCteEditar);

	boolean borrar(MovimientoCte movimientoCteB);

	List<MovimientoCte> buscar(String conceptoB, Integer tipoMovimientoB, Date fechaInicioB, Date fechaFinB,
			Integer clienteB);

}
