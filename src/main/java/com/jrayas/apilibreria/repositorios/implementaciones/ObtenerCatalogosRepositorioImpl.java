package com.jrayas.apilibreria.repositorios.implementaciones;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.model.entidadesbd.Autor;
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
			listAutor = jdbcTemplate.query("SELECT PK_AUTOR, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO FROM AUTORES",
					new Autor());
		} catch (DataAccessException e) {
			listAutor = Collections.emptyList();
			e.printStackTrace();
		}
		return listAutor;
	}

	@Override
	public List<Idioma> obtenerIdiomas() {
		List<Idioma> listIdioma;
		try {
			listIdioma = jdbcTemplate.query("SELECT PK_IDIOMA, IDIOMA FROM IDIOMAS",
					new Idioma());
		} catch (DataAccessException e) {
			listIdioma = Collections.emptyList();
			e.printStackTrace();
		}
		return listIdioma;
	}

	@Override
	public List<Genero> obtenerGeneros() {
		List<Genero> listGeneros;
		try {
			listGeneros = jdbcTemplate.query("SELECT PK_GENERO, GENERO FROM GENEROS",
					new Genero());
		} catch (DataAccessException e) {
			listGeneros = Collections.emptyList();
			e.printStackTrace();
		}
		return listGeneros;
	}

}
