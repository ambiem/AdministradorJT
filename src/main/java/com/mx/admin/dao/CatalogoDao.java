package com.mx.admin.dao;

import java.util.List;

import com.mx.admin.bean.Catalogo;

public interface CatalogoDao {
	
	List<Catalogo> buscar(String conceptoB, Integer clasificacionB, String empresaB);

	boolean agregar(Catalogo catalogo);
	
	List<Catalogo> consulta();
	
	boolean borrar(Catalogo catalogoB);
}
