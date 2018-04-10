package com.mx.admin.dao;

import java.util.Date;
import java.util.List;

import com.mx.admin.bean.EntradaSalida;

public interface EntradaSalidaDao {

	List<EntradaSalida> consulta();

	Boolean agregar(EntradaSalida operacion);

	Boolean editar(EntradaSalida operacion);

	Boolean borrar(EntradaSalida operacion);
	
	List<EntradaSalida> buscar(String conceptoB, Integer tipoMovimientoB, Date fechaInicioB, Date fechaFinB, Integer clienteB);

}
