package com.jrayas.apilibreria.servicios;

import org.apache.coyote.BadRequestException;

import com.jrayas.apilibreria.model.entidadesbd.ObtenerLibroEntidad;
import com.jrayas.apilibreria.model.entidadesjson.ObtenerLibrosRespuesta;

public interface ObtenerLibrosServicio {
	public ObtenerLibrosRespuesta obtenerLibros(ObtenerLibroEntidad peticion) throws BadRequestException;
}
