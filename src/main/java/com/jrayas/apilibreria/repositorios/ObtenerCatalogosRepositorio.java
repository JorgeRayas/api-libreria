package com.jrayas.apilibreria.repositorios;

import java.util.List;

import com.jrayas.apilibreria.model.entidadesbd.Autor;
import com.jrayas.apilibreria.model.entidadesbd.Editorial;
import com.jrayas.apilibreria.model.entidadesbd.Genero;
import com.jrayas.apilibreria.model.entidadesbd.Idioma;

public interface ObtenerCatalogosRepositorio {
	public List<Autor> obtenerAutores();

	public List<Idioma> obtenerIdiomas();

	public List<Genero> obtenerGeneros();
	
	public List<Editorial> obtenerEditoriales();
}
