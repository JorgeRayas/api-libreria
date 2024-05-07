package com.jrayas.apilibreria.model.entidadesjson;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Idioma {
	private Integer id;
	private String nombre;
}
