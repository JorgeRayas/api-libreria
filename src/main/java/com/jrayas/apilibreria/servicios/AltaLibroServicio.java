package com.jrayas.apilibreria.servicios;

import com.jrayas.apilibreria.model.AltaLibroPeticion;
import com.jrayas.apilibreria.model.AltaLibroRespuesta;

/**
 * Servicio de alta de libros
 * @author jorge
 */
public interface AltaLibroServicio {
    
    public AltaLibroRespuesta altaLibro(AltaLibroPeticion altaLibroPeticion);
}
