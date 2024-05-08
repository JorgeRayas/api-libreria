package com.jrayas.apilibreria.servicios.implementaciones;

import org.apache.coyote.BadRequestException;

import com.jrayas.apilibreria.model.entidadesjson.Libro;

public interface LibroValidacionesServicio {
	public void validarLibro(Libro libro) throws BadRequestException;
}
