package com.jrayas.apilibreria.servicios.implementaciones;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;
import com.jrayas.apilibreria.model.entidadesbd.ObtenerLibroEntidad;
import com.jrayas.apilibreria.model.entidadesjson.Libro;
import com.jrayas.apilibreria.model.entidadesjson.ObtenerLibrosRespuesta;
import com.jrayas.apilibreria.repositorios.ObtenerLibrosRepositorio;
import com.jrayas.apilibreria.servicios.ObtenerLibrosServicio;

@Service("obtenerLibrosServicio")
public class ObtenerLibrosServicioImpl implements ObtenerLibrosServicio {

	private ObtenerLibrosRepositorio repObtenerLibros;

	public ObtenerLibrosServicioImpl(
			@Autowired @Qualifier("obtenerLibrosRepositorio") ObtenerLibrosRepositorio repObtenerLibros) {
		this.repObtenerLibros = repObtenerLibros;
	}

	@Override
	public ObtenerLibrosRespuesta obtenerLibros(ObtenerLibroEntidad peticion) throws BadRequestException {
		validaciones(peticion);

		Integer totalRegistros = repObtenerLibros.obtenerTotalLibros(peticion);
		List<LibroEntidad> listLibrosEnt = repObtenerLibros.obtenerLibros(peticion);

		List<Libro> listLibros = listLibrosEnt.stream()
				.map(entLibro -> Libro.builder().clave(entLibro.getClave()).titulo(entLibro.getTitulo())
						.autor(entLibro.getAutor()).genero(entLibro.getGenero())
						.fechaPublicacion(entLibro.getFechaPublicacion()).isbn(entLibro.getIsbn())
						.sinopsis(entLibro.getSinopsis()).paginas(entLibro.getPaginas()).idioma(entLibro.getIdioma())
						.precio(entLibro.getPrecio()).existencias(entLibro.getExistencias())
						.editorial(entLibro.getEditorial()).build())
				.toList();

		return ObtenerLibrosRespuesta.builder().totalRegistros(totalRegistros).libros(listLibros).build();
	}

	private void validaciones(ObtenerLibroEntidad peticion) throws BadRequestException {

		String clave = peticion.getClave();
		if (StringUtils.isNotBlank(clave)
				&& (!StringUtils.isAlpha(clave.substring(0, 1)) || !StringUtils.isAllUpperCase(clave.substring(0, 1))
						|| !StringUtils.isNumeric(clave.substring(1)) || StringUtils.length(clave) != 3)) {
			throw new BadRequestException("La clave del libro es incorrecta");
		}

		String titulo = peticion.getTitulo();
		if (titulo != null && StringUtils.isBlank(titulo)) {
			throw new BadRequestException("El título no es válido");
		}

		String isbn = peticion.getIsbn();
		if (StringUtils.isNotBlank(isbn) && !StringUtils.isNumeric(isbn)) {
			throw new BadRequestException("El ISBN del libro es incorrecto");
		}

		validarParametroEntero(peticion.getAutor(), "El autor no es válido");
		validarParametroEntero(peticion.getGenero(), "El género no es válido");
		validarParametroEntero(peticion.getIdioma(), "El idioma no es válido");
		validarParametroEntero(peticion.getEditorial(), "La editorial no es válida");
		validarParametroEntero(peticion.getPagina(), "La página no es válida");

		LocalDate fechaInicial = peticion.getFechaPublicacionInicial();
		LocalDate fechaFinal = peticion.getFechaPublicacionFinal();

		if ((fechaInicial != null && fechaFinal == null) || (fechaInicial == null && fechaFinal != null)) {
			throw new BadRequestException(
					"Se deben inidcar fechas de inicio y fin para consultar por fecha de publicacion");
		}

		if (fechaInicial != null && fechaInicial.compareTo(fechaFinal) > 0) {
			throw new BadRequestException("La fecha inicial no puede ser mayor a la fecha final de búsqueda");
		}

	}

	private void validarParametroEntero(Integer entero, String mensaje) throws BadRequestException {
		if (entero != null && entero < 1) {
			throw new BadRequestException(mensaje);
		}
	}

}
