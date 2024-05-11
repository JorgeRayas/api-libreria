package com.jrayas.apilibreria.controlador.rest;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.AltaLibroRespuesta;
import com.jrayas.apilibreria.model.entidadesjson.CambioLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.ObtenerCatalogosRespuesta;
import com.jrayas.apilibreria.servicios.AltaLibroServicio;
import com.jrayas.apilibreria.servicios.CambioLibroServicio;
import com.jrayas.apilibreria.servicios.EliminaLibroServicio;
import com.jrayas.apilibreria.servicios.ObtenerCatalogosServicio;

@RestController
public class ApiLibreriaControlador {

	private AltaLibroServicio servAltaLibro;
	private CambioLibroServicio servCambioLibro;
	private EliminaLibroServicio servEliminaLibro;
	private ObtenerCatalogosServicio servObtenerCatalogos;

	public ApiLibreriaControlador(@Autowired @Qualifier("altaLibroServicio") AltaLibroServicio servAltaLibroServicio,
			@Autowired @Qualifier("cambioLibroServicio") CambioLibroServicio servCambioLibro,
			@Autowired @Qualifier("eliminaLibroServicio") EliminaLibroServicio servEliminaLibro,
			@Autowired @Qualifier("obtenerCatalogosServicio") ObtenerCatalogosServicio servObtenerCatalogos) {
		this.servAltaLibro = servAltaLibroServicio;
		this.servCambioLibro = servCambioLibro;
		this.servObtenerCatalogos = servObtenerCatalogos;
		this.servEliminaLibro = servEliminaLibro;
	}

	@GetMapping("/catalogos")
	public ResponseEntity<ObtenerCatalogosRespuesta> obtenerCatalogos() {
		return new ResponseEntity<>(servObtenerCatalogos.obtenerCatalogos(), HttpStatus.OK);
	}

	@PostMapping("/libro")
	public ResponseEntity<AltaLibroRespuesta> altaLibro(@RequestBody AltaLibroPeticion peticion)
			throws BadRequestException {
		return new ResponseEntity<>(servAltaLibro.altaLibro(peticion), HttpStatus.OK);
	}

	@PutMapping("/libro/{clave_id}")
	public ResponseEntity<Void> cambioLibro(@RequestBody CambioLibroPeticion peticion,
			@PathVariable("clave_id") String clave) throws BadRequestException {
		servCambioLibro.cambioLibro(peticion, clave);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/libro/{clave_id}")
	public ResponseEntity<Void> eliminarLibro(@PathVariable("clave_id") String clave) throws BadRequestException {
		servEliminaLibro.eliminarLibro(clave);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
