package com.mx.admin.dao;

import java.util.List;

import com.mx.admin.bean.Cliente;

public interface ClienteDao {

	List<Cliente> consulta();

	List<Cliente> buscar(String conceptoB, String nombre);

	boolean agregar(Cliente cliente);

	boolean borrar(Cliente cliente);

	boolean editar(Cliente clienteEditar);

	Cliente obtenerCliente(Integer idCliente);
}
