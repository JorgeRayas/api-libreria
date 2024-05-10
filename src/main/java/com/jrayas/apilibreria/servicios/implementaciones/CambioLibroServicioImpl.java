package com.jrayas.apilibreria.servicios.implementaciones;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;
import com.jrayas.apilibreria.model.entidadesjson.CambioLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.Libro;
import com.jrayas.apilibreria.repositorios.CambioLibroRepositorio;
import com.jrayas.apilibreria.servicios.CambioLibroServicio;
import com.jrayas.apilibreria.servicios.LibroValidacionesServicio;

@Transactional
@Service("cambioLibroServicio")
public class CambioLibroServicioImpl implements CambioLibroServicio {
	private CambioLibroRepositorio repCambioLibro;
	private LibroValidacionesServicio serLibroValidaciones;

	public CambioLibroServicioImpl(@Autowired @Qualifier("cambioLibroRepositorio") CambioLibroRepositorio repCambioLibro,
			@Autowired @Qualifier("libroValidacionesServicio") LibroValidacionesServicio serLibroValidaciones) {
		this.repCambioLibro = repCambioLibro;
		this.serLibroValidaciones = serLibroValidaciones;
	}

	@Override
	public void cambioLibro(CambioLibroPeticion cambioLibroPeticion, String clave) throws BadRequestException {

		serLibroValidaciones.validarPeticionLibro(cambioLibroPeticion, clave);

		LibroEntidad entLibro = convertirEntidad(cambioLibroPeticion.getLibro());
		entLibro.setClave(clave);
		
		repCambioLibro.cambioLibro(entLibro);

	}

	private LibroEntidad convertirEntidad(Libro libro) {
		return LibroEntidad.builder().clave(libro.getClave()).titulo(libro.getTitulo()).autor(libro.getAutor()).genero(libro.getGenero())
				.fechaPublicacion(libro.getFechaPublicacion()).isbn(libro.getIsbn()).sinopsis(libro.getSinopsis())
				.paginas(libro.getPaginas()).idioma(libro.getIdioma()).precio(libro.getPrecio())
				.existencias(libro.getExistencias()).editorial(libro.getEditorial()).build();
	}



}
