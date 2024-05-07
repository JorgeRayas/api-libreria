package com.jrayas.apilibreria.model.entidadesjson;

import java.util.List;

import com.jrayas.apilibreria.model.entidadesjson.Autor;

import lombok.Builder;
import lombok.Data;

/**
 * Envoltorio de respuesta de catalogos
 */
@Builder
@Data
public class ObtenerCatalogosRespuesta {

	private List<Autor> autores;
	private List<Genero> generos;
	private List<Idioma> idiomas;
	private List<Editorial> editoriales;
}
