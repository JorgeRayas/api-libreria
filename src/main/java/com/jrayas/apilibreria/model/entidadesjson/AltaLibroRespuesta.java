package com.jrayas.apilibreria.model.entidadesjson;

import lombok.Builder;
import lombok.Data;


/**
 * Envoltorio de respuesta de alta de libros
 */
@Builder
@Data
public class AltaLibroRespuesta {
    private Libro libro;
}
