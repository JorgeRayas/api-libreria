package com.jrayas.apilibreria.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autor implements RowMapper<Autor> {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    
    @Override
    public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Autor(rs.getInt("PK_AUTOR"), rs.getString("NOMBRE"),
                rs.getString("APELLIDO_PATERNO"), rs.getString("APELLIDO_MATERNO"));
    }
    
}
