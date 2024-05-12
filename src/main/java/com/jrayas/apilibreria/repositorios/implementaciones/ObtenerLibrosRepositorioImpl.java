package com.jrayas.apilibreria.repositorios.implementaciones;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;
import com.jrayas.apilibreria.model.entidadesbd.ObtenerLibroEntidad;
import com.jrayas.apilibreria.repositorios.ObtenerLibrosRepositorio;

@Repository("obtenerLibrosRepositorio")
public class ObtenerLibrosRepositorioImpl implements ObtenerLibrosRepositorio {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public ObtenerLibrosRepositorioImpl(
			@Autowired @Qualifier("mysqlNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<LibroEntidad> obtenerLibros(ObtenerLibroEntidad entObtenerLibro) {
		MapSqlParameterSource mapParametros = new MapSqlParameterSource("PAGINA",
				(entObtenerLibro.getPagina() - 1) * 20);
		StringBuilder strConsulta = new StringBuilder()
				.append("SELECT PK_LIBRO, CLAVE_ID, TITULO, FK_AUTOR, FK_GENERO,\n")
				.append("    FECHA_PUBLICACION, ISBN, SINOPSIS, FK_PORTADA, FK_ARCHIVO,\n")
				.append("    PAGINAS, FK_IDIOMA, PRECIO, EXISTENCIAS, FK_EDITORIAL\n").append("FROM LIBROS ")
				.append(generarWhere(entObtenerLibro, mapParametros)).append("\nLIMIT 20 OFFSET :PAGINA;");
		List<LibroEntidad> listEditoriales;
		try {
			listEditoriales = jdbcTemplate.query(strConsulta.toString(), mapParametros, new LibroEntidad());
		} catch (DataAccessException e) {
			throw new RuntimeException("No se pudo realizar la búsqueda en base de datos", e);
		}

		if (listEditoriales.isEmpty()) {
			throw new NoSuchElementException("No se encontraron libros con los criterios indicados");
		}

		return listEditoriales;

	}

	@Override
	public Integer obtenerTotalLibros(ObtenerLibroEntidad entObtenerLibro) {
		MapSqlParameterSource mapParametros = new MapSqlParameterSource();
		StringBuilder strConsulta = new StringBuilder().append("SELECT COUNT(*) FROM LIBROS ")
				.append(generarWhere(entObtenerLibro, mapParametros));
		try {
			return jdbcTemplate.queryForObject(strConsulta.toString(), mapParametros, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontraron libros con los criterios indicados");
		}

	}

	private String generarWhere(ObtenerLibroEntidad entObtenerLibro, MapSqlParameterSource mapParametros) {

		StringBuilder strWhere = new StringBuilder();
		boolean isPrimerCampo = true;

		isPrimerCampo = agregarCampo(entObtenerLibro.getClave(), mapParametros, strWhere, isPrimerCampo, "CLAVE_ID");
		isPrimerCampo = agregarCampo(entObtenerLibro.getAutor(), mapParametros, strWhere, isPrimerCampo, "FK_AUTOR");
		isPrimerCampo = agregarCampo(entObtenerLibro.getGenero(), mapParametros, strWhere, isPrimerCampo, "FK_GENERO");
		isPrimerCampo = agregarCampo(entObtenerLibro.getTitulo(), mapParametros, strWhere, isPrimerCampo, "TITULO");
		isPrimerCampo = agregarCampo(entObtenerLibro.getIdioma(), mapParametros, strWhere, isPrimerCampo, "FK_IDIOMA");
		isPrimerCampo = agregarCampo(entObtenerLibro.getIsbn(), mapParametros, strWhere, isPrimerCampo, "ISBN");
		isPrimerCampo = agregarCampo(entObtenerLibro.getEditorial(), mapParametros, strWhere, isPrimerCampo,
				"FK_EDITORIAL");

		if (entObtenerLibro.getFechaPublicacionInicial() != null) {
			strWhere.append(isPrimerCampo ? "\nWHERE " : "\n    AND ")
					.append("FECHA_PUBLICACION BETWEEN :FECHA_INICIAL AND :FECHA_FINAL");
			mapParametros.addValue("FECHA_INICIAL", entObtenerLibro.getFechaPublicacionInicial())
					.addValue("FECHA_FINAL", entObtenerLibro.getFechaPublicacionFinal());
		}

		return strWhere.toString();
	}

	private boolean agregarCampo(Object campo, MapSqlParameterSource mapParametros, StringBuilder strWhere,
			boolean isPrimerCampo, String nombreCampo) {
		if (campo != null) {
			strWhere.append(isPrimerCampo ? "\nWHERE " : "\n    AND ").append(nombreCampo).append(" = :")
					.append(nombreCampo);
			mapParametros.addValue(nombreCampo, campo);
			isPrimerCampo = false;
		}
		return isPrimerCampo;
	}

}
