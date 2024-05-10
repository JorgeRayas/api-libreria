package com.jrayas.apilibreria.servicios;

import org.apache.coyote.BadRequestException;

import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.CambioLibroPeticion;

public interface LibroValidacionesServicio {
	public void validarPeticionLibro(AltaLibroPeticion peticion) throws BadRequestException;
	public void validarPeticionLibro(CambioLibroPeticion peticion, String clave) throws BadRequestException;
}
