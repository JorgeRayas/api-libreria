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
public class Genero implements RowMapper<Genero> {
	private Integer id;
	private String nombre;

	@Override
	public Genero mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Genero(rs.getInt("PK_GENERO"), rs.getString("GENERO"));
	}
}
