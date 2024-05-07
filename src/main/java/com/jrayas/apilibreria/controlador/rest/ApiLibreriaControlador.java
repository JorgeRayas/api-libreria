package com.jrayas.apilibreria.controlador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.AltaLibroRespuesta;
import com.jrayas.apilibreria.model.entidadesjson.ObtenerCatalogosRespuesta;
import com.jrayas.apilibreria.servicios.AltaLibroServicio;
import com.jrayas.apilibreria.servicios.ObtenerCatalogosServicio;

@RestController
public class ApiLibreriaControlador {

	private AltaLibroServicio servAltaLibro;
	private ObtenerCatalogosServicio servObtenerCatalogos;

	public ApiLibreriaControlador(@Autowired @Qualifier("altaLibroServicio") AltaLibroServicio servAltaLibroServicio,
			@Autowired @Qualifier("obtenerCatalogosServicio") ObtenerCatalogosServicio servObtenerCatalogos) {
		this.servAltaLibro = servAltaLibroServicio;
		this.servObtenerCatalogos = servObtenerCatalogos;
	}

	@GetMapping("/catalogos")
	public ResponseEntity<ObtenerCatalogosRespuesta> obtenerCatalogos() {
		return new ResponseEntity<>(servObtenerCatalogos.obtenerCatalogos(), HttpStatus.OK);
	}

	@GetMapping("/libro")
	public ResponseEntity<AltaLibroRespuesta> altaLibro() {
		return new ResponseEntity<>(servAltaLibro.altaLibro(AltaLibroPeticion.builder().build()), HttpStatus.OK);
	}

}
