package com.jrayas.apilibreria.model.entidadesjson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Envoltorio de peticion de alta de libros
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AltaLibroPeticion {
    private Libro libro;
}
