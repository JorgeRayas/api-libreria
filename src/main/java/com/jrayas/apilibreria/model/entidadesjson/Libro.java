package com.jrayas.apilibreria.model.entidadesjson;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Libro {
	private Integer id;
    private String clave;
    private String titulo;
    private Integer autor;
    private Integer genero;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaPublicacion;
    private String isbn;
    private String sinopsis;
    private Integer paginas;
    private Integer idioma;
    private Float precio;
    private Integer existencias;
    private Integer editorial;
}
