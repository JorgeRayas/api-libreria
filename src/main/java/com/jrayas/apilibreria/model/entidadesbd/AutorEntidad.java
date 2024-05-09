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
public class AutorEntidad implements RowMapper<AutorEntidad> {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    
    @Override
    public AutorEntidad mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AutorEntidad(rs.getInt("PK_AUTOR"), rs.getString("NOMBRE"),
                rs.getString("APELLIDO_PATERNO"), rs.getString("APELLIDO_MATERNO"));
    }
    
}
