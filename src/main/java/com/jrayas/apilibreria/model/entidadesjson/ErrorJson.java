package com.jrayas.apilibreria.model.entidadesjson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorJson {
	private String status;
	private String mensaje;
	private String error;
}
