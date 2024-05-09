package com.jrayas.apilibreria.repositorios.implementaciones;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.model.entidadesbd.AutorEntidad;
import com.jrayas.apilibreria.model.entidadesbd.EditorialEntidad;
import com.jrayas.apilibreria.model.entidadesbd.GeneroEntidad;
import com.jrayas.apilibreria.model.entidadesbd.IdiomaEntidad;
import com.jrayas.apilibreria.repositorios.ObtenerCatalogosRepositorio;

@Repository("obtenerCatalogosRepositorio")
public class ObtenerCatalogosRepositorioImpl implements ObtenerCatalogosRepositorio {

	private NamedParameterJdbcTemplate jdbcTemplate;

	public ObtenerCatalogosRepositorioImpl(
			@Autowired @Qualifier("mysqlNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<AutorEntidad> obtenerAutores() {
		List<AutorEntidad> listAutor;
		try {
			listAutor = jdbcTemplate.query(
					"SELECT PK_AUTOR, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO FROM AUTORES ORDER BY 1", new AutorEntidad());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de autores");
		}
		return listAutor;
	}

	@Override
	public List<IdiomaEntidad> obtenerIdiomas() {
		List<IdiomaEntidad> listIdioma;
		try {
			listIdioma = jdbcTemplate.query("SELECT PK_IDIOMA, IDIOMA FROM IDIOMAS ORDER BY 1", new IdiomaEntidad());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de idiomas");
		}
		return listIdioma;
	}

	@Override
	public List<GeneroEntidad> obtenerGeneros() {
		List<GeneroEntidad> listGeneros;
		try {
			listGeneros = jdbcTemplate.query("SELECT PK_GENERO, GENERO FROM GENEROS ORDER BY 1", new GeneroEntidad());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de géneros");
		}
		return listGeneros;
	}

	@Override
	public List<EditorialEntidad> obtenerEditoriales() {
		List<EditorialEntidad> listEditoriales;
		try {
			listEditoriales = jdbcTemplate.query("SELECT PK_EDITORIAL, EDITORIAL FROM EDITORIALES ORDER BY 1",
					new EditorialEntidad());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron datos de editoriales");
		}
		return listEditoriales;
	}
}
