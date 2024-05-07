package com.jrayas.apilibreria.servicios.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.AltaLibroRespuesta;
import com.jrayas.apilibreria.model.entidadesjson.Libro;
import com.jrayas.apilibreria.repositorios.AltaLibroRepositorio;
import com.jrayas.apilibreria.servicios.AltaLibroServicio;

@Service("altaLibroServicio")
public class AltaLibroServicioImpl implements AltaLibroServicio {
    private AltaLibroRepositorio altaLibroRepositorio;
    
    public AltaLibroServicioImpl(
            @Autowired @Qualifier("altaLibroRepositorio") AltaLibroRepositorio altaLibroRepositorio) {
        this.altaLibroRepositorio = altaLibroRepositorio;
    }
    
    @Override
    public AltaLibroRespuesta altaLibro(AltaLibroPeticion altaLibroPeticion) {
        
        altaLibroRepositorio.altaLibro(null);
        
        return AltaLibroRespuesta.builder()
                .libro(Libro.builder().titulo("Ejemplo").autor(3).build()).build();
    }
    
}
