package com.mx.admin.dao;

import java.util.Date;
import java.util.List;

import com.mx.admin.bean.Cheque;

public interface ChequesDao {

	List<Cheque> consulta();

	boolean agregar(Cheque cheques);

	boolean editar(Cheque chequesEditar);

	boolean borrar(Cheque chequesB);

	List<Cheque> buscar(String conceptoB, Date fechaInicioB, Date fechaFinB, Integer clienteB);

}
