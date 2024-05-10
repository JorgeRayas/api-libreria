package com.jrayas.apilibreria.servicios.implementaciones;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrayas.apilibreria.model.entidadesbd.LibroEntidad;
import com.jrayas.apilibreria.model.entidadesjson.AltaLibroPeticion;
import com.jrayas.apilibreria.model.entidadesjson.AltaLibroRespuesta;
import com.jrayas.apilibreria.model.entidadesjson.Libro;
import com.jrayas.apilibreria.repositorios.AltaLibroRepositorio;
import com.jrayas.apilibreria.servicios.AltaLibroServicio;
import com.jrayas.apilibreria.servicios.LibroValidacionesServicio;

@Transactional
@Service("altaLibroServicio")
public class AltaLibroServicioImpl implements AltaLibroServicio {
	private AltaLibroRepositorio repAltaLibro;
	private LibroValidacionesServicio serLibroValidaciones;

	public AltaLibroServicioImpl(@Autowired @Qualifier("altaLibroRepositorio") AltaLibroRepositorio repAltaLibro,
			@Autowired @Qualifier("libroValidacionesServicio") LibroValidacionesServicio serLibroValidaciones) {
		this.repAltaLibro = repAltaLibro;
		this.serLibroValidaciones = serLibroValidaciones;
	}

	@Override
	public AltaLibroRespuesta altaLibro(AltaLibroPeticion altaLibroPeticion) throws BadRequestException {

		serLibroValidaciones.validarPeticionLibro(altaLibroPeticion);

		LibroEntidad entLibro = convertirEntidad(altaLibroPeticion.getLibro());

		entLibro.setClave(generarClaveLibro());

		Integer id = repAltaLibro.altaLibro(entLibro);

		return AltaLibroRespuesta.builder().libro(Libro.builder().id(id).clave(entLibro.getClave()).build()).build();
	}

	private LibroEntidad convertirEntidad(Libro libro) {
		return LibroEntidad.builder().titulo(libro.getTitulo()).autor(libro.getAutor()).genero(libro.getGenero())
				.fechaPublicacion(libro.getFechaPublicacion()).isbn(libro.getIsbn()).sinopsis(libro.getSinopsis())
				.paginas(libro.getPaginas()).idioma(libro.getIdioma()).precio(libro.getPrecio())
				.existencias(libro.getExistencias()).editorial(libro.getEditorial()).build();
	}

	/**
	 * Genera una clave unica alfanumerica iniciando con letra [A-Z] seguido de dos
	 * numeros [0-9]
	 * 
	 * @return clave
	 */
	private String generarClaveLibro() {
		String strClave;
		do {
			strClave = new StringBuilder().append(RandomStringUtils.random(1, true, false).toUpperCase())
					.append(RandomStringUtils.random(2, false, true)).toString();
		} while (!repAltaLibro.consultaClaveDisponible(strClave));
		return strClave;
	}

}
