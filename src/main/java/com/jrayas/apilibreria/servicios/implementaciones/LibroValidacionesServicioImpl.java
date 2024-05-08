package com.jrayas.apilibreria.servicios.implementaciones;

import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrayas.apilibreria.model.entidadesjson.Libro;
import com.jrayas.apilibreria.repositorios.ValidacionesRepositorio;

@Service("libroValidacionesServicio")
public class LibroValidacionesServicioImpl implements LibroValidacionesServicio {

	private ValidacionesRepositorio repValidaciones;

	public LibroValidacionesServicioImpl(
			@Autowired @Qualifier("validacionesRepositorio") ValidacionesRepositorio repValidaciones) {
		this.repValidaciones = repValidaciones;
	}

	@Override
	public void validarLibro(Libro libro) throws BadRequestException {

		// Se valida que los datos obligatorios de la peticion sean validos
		if (libro == null || StringUtils.isBlank(libro.getTitulo()) || StringUtils.isBlank(libro.getIsbn())
				|| StringUtils.isBlank(libro.getSinopsis()) || !esEnteroValido(libro.getAutor())
				|| !esEnteroValido(libro.getGenero()) || !esEnteroValido(libro.getPaginas())
				|| !esEnteroValido(libro.getIdioma()) || !esEnteroValido(libro.getExistencias())
				|| !esEnteroValido(libro.getEditorial()) || !esFlotanteValido(libro.getPrecio())) {
			throw new BadRequestException("Los datos de la petición no son correctos");
		}

		// Se valida que las claves indicadas sean validas, se consultan catalogos
		repValidaciones.validarAutor(libro.getAutor());
		repValidaciones.validarGenero(libro.getGenero());
		repValidaciones.validarIdioma(libro.getIdioma());
		repValidaciones.validarEditorial(libro.getEditorial());

	}

	private boolean esEnteroValido(Integer numero) {
		return numero != null && numero > 0;
	}

	private boolean esFlotanteValido(Float flotante) {
		return flotante != null && flotante > 0.0f;
	}

}
