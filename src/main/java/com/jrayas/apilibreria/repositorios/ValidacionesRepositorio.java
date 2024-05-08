package com.jrayas.apilibreria.repositorios;

public interface ValidacionesRepositorio {
	public void validarAutor(Integer id);

	public void validarGenero(Integer id);

	public void validarIdioma(Integer id);

	public void validarEditorial(Integer id);
}
