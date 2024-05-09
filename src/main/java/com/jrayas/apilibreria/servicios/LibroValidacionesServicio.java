package com.jrayas.apilibreria.servicios;

import org.apache.coyote.BadRequestException;

import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;

public interface LibroValidacionesServicio {
	public void validarPeticionLibro(AltaLibroPeticion peticion) throws BadRequestException;
	//public void validarPeticionLibro(CambioLibroPeticion) throws BadRequestException;
}
