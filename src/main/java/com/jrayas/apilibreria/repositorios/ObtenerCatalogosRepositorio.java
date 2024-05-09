package com.jrayas.apilibreria.repositorios;

import java.util.List;

import com.jrayas.apilibreria.model.entidadesbd.AutorEntidad;
import com.jrayas.apilibreria.model.entidadesbd.EditorialEntidad;
import com.jrayas.apilibreria.model.entidadesbd.GeneroEntidad;
import com.jrayas.apilibreria.model.entidadesbd.IdiomaEntidad;

public interface ObtenerCatalogosRepositorio {
	public List<AutorEntidad> obtenerAutores();

	public List<IdiomaEntidad> obtenerIdiomas();

	public List<GeneroEntidad> obtenerGeneros();
	
	public List<EditorialEntidad> obtenerEditoriales();
}
