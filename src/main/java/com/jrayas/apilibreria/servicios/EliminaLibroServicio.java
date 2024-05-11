package com.jrayas.apilibreria.servicios;

import org.apache.coyote.BadRequestException;

public interface EliminaLibroServicio {
	public void eliminarLibro(String clave) throws BadRequestException ;
}
