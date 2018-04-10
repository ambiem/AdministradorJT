package com.mx.admin.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mx.admin.bean.Cotizacion;
import com.mx.admin.viewBean.CotizacionView;

public interface CotizacionDao {
	
	List<Cotizacion> buscar(Integer clienteB, Integer empresaB, Date fechaInicioB, Date fechaFinB);

	boolean agregar(CotizacionView operacion);
	
	List<Cotizacion> consulta();
	
	boolean borrar(Cotizacion cotizacionB);
	
	boolean editar(Cotizacion cotizacionEditar);

}
