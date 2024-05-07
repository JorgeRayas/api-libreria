package com.jrayas.apilibreria.model.entidadesjson;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Autor {
	private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
}
