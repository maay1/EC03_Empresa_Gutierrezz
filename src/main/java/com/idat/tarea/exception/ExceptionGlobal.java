package com.idat.tarea.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobal {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String manejarArgumentosInvalidos(IllegalArgumentException ex, Model model) {
		model.addAttribute("titulo", "Solicitud invalida");
		model.addAttribute("mensaje", ex.getMessage());
		model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
		return "error400";
	}
	
	@ExceptionHandler(InvalidArgumentException.class)
	public String manejarArgumentosCustom(InvalidArgumentException ex, Model modelo) {
		modelo.addAttribute("titulo", "Argumento incorrecto");
		modelo.addAttribute("mensaje", ex.getMessage());
		modelo.addAttribute("status", 404);
		return "error/error404";
	}
	
	@ExceptionHandler(Exception.class)
	public String manejarErrorGeneral(Exception ex, Model modelo) {
		modelo.addAttribute("titulo", "Error inesperado");
		modelo.addAttribute("mensaje", ex.getMessage());
		modelo.addAttribute("status", 500);
		return "error/error500";
	}
}
