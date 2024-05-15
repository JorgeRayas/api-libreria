package com.jrayas.apilibreria.model.entidadesbd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroEntidad implements RowMapper<LibroEntidad> {
	private Integer id;
	private String clave;
	private String titulo;
	private Integer autor;
	private Integer genero;
	private LocalDate fechaPublicacion;
	private String isbn;
	private String sinopsis;
	private Integer paginas;
	private Integer idioma;
	private Float precio;
	private Integer existencias;
	private Integer editorial;

	@Override
	public LibroEntidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		return LibroEntidad.builder().id(rs.getInt("PK_LIBRO")).clave(rs.getString("CLAVE_ID"))
				.titulo(rs.getString("TITULO")).autor(rs.getInt("FK_AUTOR")).genero(rs.getInt("FK_GENERO"))
				.fechaPublicacion(rs.getDate("FECHA_PUBLICACION").toLocalDate()).isbn(rs.getString("ISBN"))
				.sinopsis(rs.getString("SINOPSIS")).paginas(rs.getInt("PAGINAS")).idioma(rs.getInt("FK_IDIOMA"))
				.precio(rs.getFloat("PRECIO")).existencias(rs.getInt("EXISTENCIAS"))
				.editorial(rs.getInt("FK_EDITORIAL")).build();
	}
}
