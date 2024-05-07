package com.jrayas.apilibreria.servicios;

import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.AltaLibroRespuesta;

/**
 * Servicio de alta de libros
 * @author jorge
 */
public interface AltaLibroServicio {
    
    public AltaLibroRespuesta altaLibro(AltaLibroPeticion altaLibroPeticion);
}
