package com.jrayas.apilibreria.servicios;

import org.apache.coyote.BadRequestException;

import com.jrayas.apilibreria.model.entidadesjson.CambioLibroPeticion;

/**
 * Servicio de cambio de libros
 * @author jorge
 */
public interface CambioLibroServicio {

    public void cambioLibro(CambioLibroPeticion cambioLibroPeticion, String clave) throws BadRequestException;
}
