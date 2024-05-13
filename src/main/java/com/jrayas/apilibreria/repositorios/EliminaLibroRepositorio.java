package com.jrayas.apilibreria.repositorios;

/**
 * Repositorio de eliminacion de libros
 *
 * @author jorge
 */
public interface EliminaLibroRepositorio {
	/**
	 * Elimina libro en base de datos
	 *
	 * @param clave
	 */
	public void eliminaLibro(String clave);

}
