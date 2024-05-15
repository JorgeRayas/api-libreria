package com.jrayas.apilibreria.repositorios.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;
import com.jrayas.apilibreria.repositorios.CambioLibroRepositorio;

@Repository("cambioLibroRepositorio")
public class CambioLibroRepositorioImpl implements CambioLibroRepositorio {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public CambioLibroRepositorioImpl(
			@Autowired @Qualifier("mysqlNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void cambioLibro(LibroEntidad libro) {
		String strConsulta = new StringBuilder().append("UPDATE LIBRERIA.LIBROS SET \n")
				.append("    TITULO = :TITULO, \n").append("    FK_AUTOR = :FK_AUTOR, \n")
				.append("    FK_GENERO = :FK_GENERO, \n").append("    FECHA_PUBLICACION = :FECHA_PUBLICACION, \n")
				.append("    ISBN = :ISBN, \n").append("    SINOPSIS = :SINOPSIS, \n")
				.append("    PAGINAS = :PAGINAS, \n").append("    FK_IDIOMA = :FK_IDIOMA, \n")
				.append("    PRECIO = :PRECIO, \n").append("    EXISTENCIAS = :EXISTENCIAS, \n")
				.append("    FK_EDITORIAL = :FK_EDITORIAL, \n").append("    FECHA_MODIFICACION = CURRENT_TIMESTAMP, \n")
				.append("    USUARIO_MODIFICACION = CURRENT_USER() \n").append("WHERE CLAVE_ID = :CLAVE_ID;")
				.toString();
		MapSqlParameterSource mapParametros = new MapSqlParameterSource().addValue("CLAVE_ID", libro.getClave())
				.addValue("TITULO", libro.getTitulo()).addValue("FK_AUTOR", libro.getAutor())
				.addValue("FK_GENERO", libro.getGenero())
				.addValue("FECHA_PUBLICACION", libro.getFechaPublicacion().toString()).addValue("ISBN", libro.getIsbn())
				.addValue("SINOPSIS", libro.getSinopsis()).addValue("PAGINAS", libro.getPaginas())
				.addValue("FK_IDIOMA", libro.getIdioma()).addValue("PRECIO", libro.getPrecio())
				.addValue("EXISTENCIAS", libro.getExistencias()).addValue("FK_EDITORIAL", libro.getEditorial());

		try {
			jdbcTemplate.update(strConsulta, mapParametros);
		} catch (DataAccessException e) {
			throw new RuntimeException("No se pudo actualizar el libro", e);
		}

	}

}
