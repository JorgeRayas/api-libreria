package com.jrayas.apilibreria.model;

import lombok.Builder;
import lombok.Data;

/**
 * Envoltorio de peticion de alta de libros
 */

@Builder
@Data
public class AltaLibroPeticion {
    private Libro libro;
}
