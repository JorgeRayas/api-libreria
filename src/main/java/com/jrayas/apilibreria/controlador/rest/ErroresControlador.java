package com.jrayas.apilibreria.controlador.rest;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jrayas.apilibreria.model.entidadesjson.ErrorJson;

@ControllerAdvice
public class ErroresControlador {

	@ExceptionHandler
	public ResponseEntity<ErrorJson> controlError(Exception exception) {
		Logger.getAnonymousLogger().log(Level.SEVERE, "Error al ejecutar el aplicativo", exception);
		return new ResponseEntity<>(
				ErrorJson.builder().status(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.error(HttpStatus.INTERNAL_SERVER_ERROR.name()).mensaje(exception.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorJson> controlError(RuntimeException exception) {
		Logger.getAnonymousLogger().log(Level.SEVERE, "Error al ejecutar el aplicativo", exception);
		return new ResponseEntity<>(
				ErrorJson.builder().status(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.error(HttpStatus.INTERNAL_SERVER_ERROR.name()).mensaje(exception.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorJson> controlError(BadRequestException exception) {
		return new ResponseEntity<>(
				ErrorJson.builder().status(Integer.toString(HttpStatus.BAD_REQUEST.value()))
						.error(HttpStatus.BAD_REQUEST.name()).mensaje(exception.getMessage()).build(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorJson> controlError(HttpMessageNotReadableException exception) {
		return new ResponseEntity<>(
				ErrorJson.builder().status(Integer.toString(HttpStatus.BAD_REQUEST.value()))
						.error(HttpStatus.BAD_REQUEST.name()).mensaje(exception.getMessage()).build(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorJson> controlError(NoSuchElementException exception) {
		return new ResponseEntity<>(
				ErrorJson.builder().status(Integer.toString(HttpStatus.NOT_FOUND.value()))
						.error(HttpStatus.NOT_FOUND.name()).mensaje(exception.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorJson> controlError(DataAccessException exception) {
		Logger.getAnonymousLogger().log(Level.SEVERE, "Error al acceder a BD", exception);
		return new ResponseEntity<>(
				ErrorJson.builder().status(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.error(HttpStatus.INTERNAL_SERVER_ERROR.name()).mensaje("Ocurrió un error al acceder a base de datos").build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
