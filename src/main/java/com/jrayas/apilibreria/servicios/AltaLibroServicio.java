package com.jrayas.apilibreria.servicios;

import org.apache.coyote.BadRequestException;

import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.AltaLibroRespuesta;

/**
 * Servicio de alta de libros
 * @author jorge
 */
public interface AltaLibroServicio {

    public AltaLibroRespuesta altaLibro(AltaLibroPeticion altaLibroPeticion) throws BadRequestException;
}
