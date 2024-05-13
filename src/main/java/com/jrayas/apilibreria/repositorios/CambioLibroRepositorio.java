package com.jrayas.apilibreria.repositorios;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;

/**
 * Repositorio de alta de libros
 *
 * @author jorge
 */
public interface CambioLibroRepositorio {
	/**
	 * Actualiza libro en base de datos
	 *
	 * @param libro
	 */
	public void cambioLibro(LibroEntidad libro);

}
