package com.jrayas.apilibreria.model.entidadesjson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

/**
 * Envoltorio de respuesta de catalogos
 */
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObtenerCatalogosRespuesta {

	private List<Autor> autores;
	private List<Genero> generos;
	private List<Idioma> idiomas;
	private List<Editorial> editoriales;
}
