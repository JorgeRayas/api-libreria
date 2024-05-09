package com.jrayas.apilibreria.model.entidadesjson;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;


/**
 * Envoltorio de respuesta de alta de libros
 */
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AltaLibroRespuesta {
    private Libro libro;
}
