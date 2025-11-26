package com.idat.tarea.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaDTO {
	
	private Long idArea;
	@NotBlank(message = "El nombre no puede estar vacío")
	private String nombre;
	private String descripcion;
	private String estado;
	
	
	
}
