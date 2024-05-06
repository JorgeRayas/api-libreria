package com.jrayas.apilibreria.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Libro {
    private String titulo;
    private Integer autor;
    private Integer genero;
    private Date fechaPublicacion;
    private String isbn;
    private String sinopsis;
    private String imagen;
    private String archivo;
    private Integer paginas;
    private String idioma;
    private Float precio;
    private String moneda;
    private Integer existencias;
    private Integer editorial;
}
