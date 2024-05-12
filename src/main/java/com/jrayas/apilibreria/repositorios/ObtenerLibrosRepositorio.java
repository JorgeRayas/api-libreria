package com.jrayas.apilibreria.repositorios;

import java.util.List;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;
import com.jrayas.apilibreria.model.entidadesbd.ObtenerLibroEntidad;

public interface ObtenerLibrosRepositorio {

	public List<LibroEntidad> obtenerLibros(ObtenerLibroEntidad entObtenerLibro);

	public Integer obtenerTotalLibros(ObtenerLibroEntidad entObtenerLibro);
}
