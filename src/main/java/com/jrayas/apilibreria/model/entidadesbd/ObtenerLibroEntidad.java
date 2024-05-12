package com.jrayas.apilibreria.model.entidadesbd;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObtenerLibroEntidad {
    private String clave;
	private String titulo;
    private Integer autor;
    private Integer genero;
    private LocalDate fechaPublicacionInicial;
    private LocalDate fechaPublicacionFinal;
    private String isbn;
    private Integer pagina;
    private Integer idioma;
    private Integer editorial;
}
