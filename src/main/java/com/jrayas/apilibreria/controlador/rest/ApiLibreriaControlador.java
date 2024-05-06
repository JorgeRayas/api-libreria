package com.jrayas.apilibreria.controlador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrayas.apilibreria.model.AltaLibroPeticion;
import com.jrayas.apilibreria.model.AltaLibroRespuesta;
import com.jrayas.apilibreria.servicios.AltaLibroServicio;

@RestController
public class ApiLibreriaControlador {
    
    private AltaLibroServicio altaLibroServicio;
    
    public ApiLibreriaControlador(
            @Autowired @Qualifier("altaLibroServicio") AltaLibroServicio altaLibroServicio) {
        this.altaLibroServicio = altaLibroServicio;
    }
    
    @GetMapping("/libro")
    public ResponseEntity<AltaLibroRespuesta> altaLibro() {
        return new ResponseEntity<>(
                altaLibroServicio.altaLibro(AltaLibroPeticion.builder().build()), HttpStatus.OK);
    }
    
}
