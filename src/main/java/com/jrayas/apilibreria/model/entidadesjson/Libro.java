package com.jrayas.apilibreria.model.entidadesjson;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private String titulo;
    private Integer autor;
    private Integer genero;
    private LocalDate fechaPublicacion;
    private String isbn;
    private String sinopsis;
    private String imagen;
    private String archivo;
    private Integer paginas;
    private Integer idioma;
    private Float precio;
    private Integer existencias;
    private Integer editorial;
}
