package com.jrayas.apilibreria.model.entidadesjson;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CambioLibroPeticion {
    private Libro libro;
}
