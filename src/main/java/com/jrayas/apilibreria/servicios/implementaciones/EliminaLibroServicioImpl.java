package com.jrayas.apilibreria.servicios.implementaciones;

import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrayas.apilibreria.repositorios.EliminaLibroRepositorio;
import com.jrayas.apilibreria.servicios.EliminaLibroServicio;

@Service("eliminaLibroServicio")
public class EliminaLibroServicioImpl implements EliminaLibroServicio {

	private EliminaLibroRepositorio repEliminaLibro;

	public EliminaLibroServicioImpl(
			@Autowired @Qualifier("eliminaLibroRepositorio") EliminaLibroRepositorio repEliminaLibro) {
		this.repEliminaLibro = repEliminaLibro;
	}

	@Override
	public void eliminarLibro(String clave) throws BadRequestException {
		if (!StringUtils.isAlpha(clave.substring(0, 1)) || !StringUtils.isAllUpperCase(clave.substring(0, 1))
				|| !StringUtils.isNumeric(clave.substring(1)) || StringUtils.length(clave) != 3) {
			throw new BadRequestException("La clave del libro es incorrecta");
		}
		
		repEliminaLibro.eliminaLibro(clave);
	}

}
