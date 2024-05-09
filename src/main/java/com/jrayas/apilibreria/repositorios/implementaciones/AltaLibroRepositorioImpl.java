package com.jrayas.apilibreria.repositorios.implementaciones;

import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;
import com.jrayas.apilibreria.repositorios.AltaLibroRepositorio;

@Repository("altaLibroRepositorio")
public class AltaLibroRepositorioImpl implements AltaLibroRepositorio {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public AltaLibroRepositorioImpl(
			@Autowired @Qualifier("mysqlNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Integer altaLibro(LibroEntidad libro) {
		String strConsulta = new StringBuilder().append("INSERT INTO LIBRERIA.LIBROS \n").append(
				"(CLAVE_ID, TITULO, FK_AUTOR, FK_GENERO, FECHA_PUBLICACION, ISBN, SINOPSIS, FK_PORTADA, FK_ARCHIVO, ")
				.append("PAGINAS, FK_IDIOMA, PRECIO, EXISTENCIAS, FK_EDITORIAL, USUARIO_REGISTRO, USUARIO_MODIFICACION) VALUES( \n")
				.append(":CLAVE_ID,:TITULO,:FK_AUTOR,:FK_GENERO,:FECHA_PUBLICACION,:ISBN,:SINOPSIS,:FK_PORTADA,:FK_ARCHIVO,")
				.append(":PAGINAS,:FK_IDIOMA,:PRECIO,:EXISTENCIAS,:FK_EDITORIAL,CURRENT_USER(),CURRENT_USER());")
				.toString();
		MapSqlParameterSource mapParametros = new MapSqlParameterSource().addValue("CLAVE_ID", libro.getClave())
				.addValue("TITULO", libro.getTitulo()).addValue("FK_AUTOR", libro.getAutor())
				.addValue("FK_GENERO", libro.getGenero())
				.addValue("FECHA_PUBLICACION", libro.getFechaPublicacion().toString()).addValue("ISBN", libro.getIsbn())
				.addValue("SINOPSIS", libro.getSinopsis()).addValue("FK_PORTADA", null).addValue("FK_ARCHIVO", null)
				.addValue("PAGINAS", libro.getPaginas()).addValue("FK_IDIOMA", libro.getIdioma())
				.addValue("PRECIO", libro.getPrecio()).addValue("EXISTENCIAS", libro.getExistencias())
				.addValue("FK_EDITORIAL", libro.getEditorial());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(strConsulta, mapParametros, keyHolder);
		} catch (DataAccessException e) {
			throw new RuntimeException("No se pudo dar de alta el libro", e);
		}

		BigInteger id = (BigInteger) keyHolder.getKey();

		return Integer.parseInt(id.toString());
	}

	@Override
	public boolean consultaClaveDisponible(String clave) {
		try {
			jdbcTemplate.queryForObject("SELECT CLAVE_ID FROM LIBROS WHERE CLAVE_ID = :CLAVE_ID",
					new MapSqlParameterSource().addValue("CLAVE_ID", clave), String.class);
			return false;
		} catch (EmptyResultDataAccessException e) {
			return true;
		}

	}

}
