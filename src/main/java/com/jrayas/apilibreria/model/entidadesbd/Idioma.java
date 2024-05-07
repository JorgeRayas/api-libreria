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
public class Idioma implements RowMapper<Idioma> {
	private Integer id;
	private String nombre;

	@Override
	public Idioma mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Idioma(rs.getInt("PK_IDIOMA"), rs.getString("IDIOMA"));
	}
}
