package com.jrayas.apilibreria.repositorios.implementaciones;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.repositorios.ValidacionesRepositorio;

@Repository("validacionesRepositorio")
public class ValidacionesRepositorioImpl implements ValidacionesRepositorio {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public ValidacionesRepositorioImpl(
			@Autowired @Qualifier("mysqlNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void validarAutor(Integer id) {

		try {
			jdbcTemplate.queryForObject("SELECT PK_AUTOR FROM AUTORES WHERE PK_AUTOR = :PK_AUTOR",
					new MapSqlParameterSource().addValue("PK_AUTOR", id), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos del autor indicado");
		}

	}

	@Override
	public void validarGenero(Integer id) {
		try {
			jdbcTemplate.queryForObject("SELECT PK_GENERO FROM GENEROS WHERE PK_GENERO = :PK_GENERO",
					new MapSqlParameterSource().addValue("PK_GENERO", id), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos del género indicado");
		}

	}

	@Override
	public void validarIdioma(Integer id) {
		try {
			jdbcTemplate.queryForObject("SELECT PK_IDIOMA FROM IDIOMAS WHERE PK_IDIOMA = :PK_IDIOMA",
					new MapSqlParameterSource().addValue("PK_IDIOMA", id), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos del idioma indicado");
		}

	}

	@Override
	public void validarEditorial(Integer id) {
		try {
			jdbcTemplate.queryForObject("SELECT PK_EDITORIAL FROM EDITORIALES WHERE PK_EDITORIAL = :PK_EDITORIAL",
					new MapSqlParameterSource().addValue("PK_EDITORIAL", id), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de la editorial indicada");
		}

	}
	
	@Override
	public String validarIsbn(String isbn) {
		try {
			return jdbcTemplate.queryForObject("SELECT CLAVE_ID FROM LIBROS WHERE ISBN = :ISBN",
					new MapSqlParameterSource().addValue("ISBN", isbn), String.class);
		} catch (EmptyResultDataAccessException e) {
			return "";
		}
	}
	
	@Override
	public void validarClave(String clave) {
		try {
			jdbcTemplate.queryForObject("SELECT CLAVE_ID FROM LIBROS WHERE CLAVE_ID = :CLAVE_ID",
					new MapSqlParameterSource().addValue("CLAVE_ID", clave), String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontró el libro con la clave indicada");
		}

	}

}
