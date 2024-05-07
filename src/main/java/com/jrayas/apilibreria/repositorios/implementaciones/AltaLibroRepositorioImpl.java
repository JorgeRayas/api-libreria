package com.jrayas.apilibreria.repositorios.implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrayas.apilibreria.model.entidadesbd.Autor;
import com.jrayas.apilibreria.model.entidadesjson.Libro;
import com.jrayas.apilibreria.repositorios.AltaLibroRepositorio;

@Repository("altaLibroRepositorio")
public class AltaLibroRepositorioImpl implements AltaLibroRepositorio {
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    public AltaLibroRepositorioImpl(
            @Autowired @Qualifier("mysqlNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void altaLibro(Libro libro) {
        List<Autor> listAutor;
        try {
            listAutor = jdbcTemplate.query("SELECT * FROM AUTORES", new Autor());
            listAutor.forEach(autor -> System.out.println(autor.toString()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
}
