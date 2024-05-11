package com.jrayas.apilibreria.servicios.implementaciones;

import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.CambioLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.Libro;
import com.jrayas.apilibreria.repositorios.ValidacionesRepositorio;
import com.jrayas.apilibreria.servicios.LibroValidacionesServicio;

@Service("libroValidacionesServicio")
public class LibroValidacionesServicioImpl implements LibroValidacionesServicio {

	private ValidacionesRepositorio repValidaciones;

	public LibroValidacionesServicioImpl(
			@Autowired @Qualifier("validacionesRepositorio") ValidacionesRepositorio repValidaciones) {
		this.repValidaciones = repValidaciones;
	}

	@Override
	public void validarPeticionLibro(AltaLibroPeticion peticion) throws BadRequestException {

		if (peticion == null) {
			throw new BadRequestException("Los datos de la petición no son correctos");
		}
		Libro libro = peticion.getLibro();
		validarLibro(libro);
		if (StringUtils.isNotBlank(repValidaciones.validarIsbn(libro.getIsbn()))) {
			throw new BadRequestException("El ISBN ya existe con otro registro, valide que el ISBN sea único.");
		}
	}

	@Override
	public void validarPeticionLibro(CambioLibroPeticion peticion, String clave) throws BadRequestException {

		if (peticion == null) {
			throw new BadRequestException("Los datos de la petición no son correctos");
		}
		Libro libro = peticion.getLibro();
		validarLibro(libro);

		if (!StringUtils.isAlpha(clave.substring(0, 1)) || !StringUtils.isAllUpperCase(clave.substring(0, 1))
				|| !StringUtils.isNumeric(clave.substring(1)) || StringUtils.length(clave) != 3) {
			throw new BadRequestException("La clave del libro es incorrecta");
		}

		repValidaciones.validarClave(clave);

		String claveBD = repValidaciones.validarIsbn(libro.getIsbn());

		if (StringUtils.isNotBlank(claveBD) && !StringUtils.equals(claveBD, clave)) {
			throw new BadRequestException("El ISBN ya existe con otro registro, valide que el ISBN sea único.");
		}
	}

	private void validarLibro(Libro libro) throws BadRequestException {
		// Se valida que los datos obligatorios de la peticion sean validos
		if (libro == null || StringUtils.isBlank(libro.getTitulo()) || !StringUtils.isNumeric(libro.getIsbn())
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
