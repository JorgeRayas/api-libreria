package com.jrayas.apilibreria.servicios.implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrayas.apilibreria.model.entidadesjson.Autor;
import com.jrayas.apilibreria.model.entidadesjson.Editorial;
import com.jrayas.apilibreria.model.entidadesjson.Genero;
import com.jrayas.apilibreria.model.entidadesjson.Idioma;
import com.jrayas.apilibreria.model.entidadesjson.ObtenerCatalogosRespuesta;
import com.jrayas.apilibreria.repositorios.ObtenerCatalogosRepositorio;
import com.jrayas.apilibreria.servicios.ObtenerCatalogosServicio;

@Service("obtenerCatalogosServicio")
public class ObtenerCatalgosServicioImpl implements ObtenerCatalogosServicio {

	private ObtenerCatalogosRepositorio repObtenerCatalogos;

	public ObtenerCatalgosServicioImpl(
			@Autowired @Qualifier("obtenerCatalogosRepositorio") ObtenerCatalogosRepositorio obtenerCatalogosRepositorio) {
		this.repObtenerCatalogos = obtenerCatalogosRepositorio;
	}

	@Override
	public ObtenerCatalogosRespuesta obtenerCatalogos() {
		List<Autor> listAutores = repObtenerCatalogos.obtenerAutores().stream()
				.map(autor -> Autor.builder().id(autor.getId()).nombre(autor.getNombre())
						.apellidoPaterno(autor.getApellidoPaterno()).apellidoMaterno(autor.getApellidoMaterno())
						.build())
				.toList();

		List<Idioma> listIdiomas = repObtenerCatalogos.obtenerIdiomas().stream()
				.map(idioma -> Idioma.builder().id(idioma.getId()).nombre(idioma.getNombre()).build()).toList();

		List<Genero> listGeneros = repObtenerCatalogos.obtenerGeneros().stream()
				.map(genero -> Genero.builder().id(genero.getId()).nombre(genero.getNombre()).build()).toList();

		List<Editorial> listEditoriales = repObtenerCatalogos.obtenerEditoriales().stream()
				.map(editorial -> Editorial.builder().id(editorial.getId()).nombre(editorial.getNombre()).build())
				.toList();

		return ObtenerCatalogosRespuesta.builder().autores(listAutores).generos(listGeneros).idiomas(listIdiomas)
				.editoriales(listEditoriales).build();
	}

}
