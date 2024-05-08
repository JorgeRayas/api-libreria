package com.jrayas.apilibreria.repositorios.implementaciones;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.model.entidadesbd.Autor;
import com.jrayas.apilibreria.model.entidadesbd.Editorial;
import com.jrayas.apilibreria.model.entidadesbd.Genero;
import com.jrayas.apilibreria.model.entidadesbd.Idioma;
import com.jrayas.apilibreria.repositorios.ObtenerCatalogosRepositorio;

@Repository("obtenerCatalogosRepositorio")
public class ObtenerCatalogosRepositorioImpl implements ObtenerCatalogosRepositorio {

	private NamedParameterJdbcTemplate jdbcTemplate;

	public ObtenerCatalogosRepositorioImpl(
			@Autowired @Qualifier("mysqlNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Autor> obtenerAutores() {
		List<Autor> listAutor;
		try {
			listAutor = jdbcTemplate.query(
					"SELECT PK_AUTOR, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO FROM AUTORES ORDER BY 1", new Autor());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de autores");
		}
		return listAutor;
	}

	@Override
	public List<Idioma> obtenerIdiomas() {
		List<Idioma> listIdioma;
		try {
			listIdioma = jdbcTemplate.query("SELECT PK_IDIOMA, IDIOMA FROM IDIOMAS ORDER BY 1", new Idioma());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de idiomas");
		}
		return listIdioma;
	}

	@Override
	public List<Genero> obtenerGeneros() {
		List<Genero> listGeneros;
		try {
			listGeneros = jdbcTemplate.query("SELECT PK_GENERO, GENERO FROM GENEROS ORDER BY 1", new Genero());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de géneros");
		}
		return listGeneros;
	}

	@Override
	public List<Editorial> obtenerEditoriales() {
		List<Editorial> listEditoriales;
		try {
			listEditoriales = jdbcTemplate.query("SELECT PK_EDITORIAL, EDITORIAL FROM EDITORIALES ORDER BY 1",
					new Editorial());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de editoriales");
		}
		return listEditoriales;
	}
}
