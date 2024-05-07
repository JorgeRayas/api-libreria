package com.jrayas.apilibreria.model.entidadesjson;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Editorial {
	private Integer id;
	private String nombre;
}
