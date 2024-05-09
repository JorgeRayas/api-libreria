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
public class EditorialEntidad implements RowMapper<EditorialEntidad> {
	private Integer id;
	private String nombre;

	@Override
	public EditorialEntidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new EditorialEntidad(rs.getInt("PK_EDITORIAL"), rs.getString("EDITORIAL"));
	}
}
