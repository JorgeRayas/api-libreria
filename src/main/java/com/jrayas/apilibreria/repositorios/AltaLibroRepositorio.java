package com.jrayas.apilibreria.repositorios;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;

/**
 * Repositorio de alta de libros
 * 
 * @author jorge
 */
public interface AltaLibroRepositorio {
	/**
	 * Registra libro en base de datos
	 * 
	 * @param libro
	 * @return id de base de datos
	 */
	public Integer altaLibro(LibroEntidad libro);

	/**
	 * Consulta que la clave indicada esté disponible para su uso
	 * 
	 * @param clave a validar
	 * @return true si está disponible, false si ya existe
	 */
	public boolean consultaClaveDisponible(String clave);
}
