package com.jrayas.apilibreria.model.entidadesbd;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroEntidad implements RowMapper<GeneroEntidad> {
	private Integer id;
	private String nombre;

	@Override
	public GeneroEntidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new GeneroEntidad(rs.getInt("PK_GENERO"), rs.getString("GENERO"));
	}
}
