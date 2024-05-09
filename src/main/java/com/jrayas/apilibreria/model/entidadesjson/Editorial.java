package com.jrayas.apilibreria.model.entidadesjson;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Editorial {
	private Integer id;
	private String nombre;
}
