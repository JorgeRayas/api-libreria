package com.jrayas.apilibreria.repositorios.implementaciones;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.repositorios.EliminaLibroRepositorio;

@Repository("eliminaLibroRepositorio")
public class EliminaLibroRepositorioImpl implements EliminaLibroRepositorio {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public EliminaLibroRepositorioImpl(
			@Autowired @Qualifier("mysqlNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void eliminaLibro(String clave) {
		int afectaciones = 0;
		try {
			afectaciones = jdbcTemplate.update("DELETE FROM LIBRERIA.LIBROS WHERE CLAVE_ID = :CLAVE_ID",
					new MapSqlParameterSource("CLAVE_ID", clave));
		} catch (DataAccessException e) {
			throw new RuntimeException("No se pudo elimanr el libro", e);
		}

		if (afectaciones == 0) {
			throw new NoSuchElementException("No se encontró el libro a eliminar");
		}
	}

}
