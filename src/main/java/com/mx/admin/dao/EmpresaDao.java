package com.mx.admin.dao;

import java.util.List;

import com.mx.admin.bean.Empresa;

public interface EmpresaDao {

	List<Empresa> consulta();

	List<Empresa> buscar(String nombreB, Integer clienteB);

	boolean agregar(Empresa empresa);

	boolean borrar(Empresa empresa);

	boolean editar(Empresa empresaEditar);

	List<Empresa> consultaEmpresasCliente(Integer idCliente);

}
