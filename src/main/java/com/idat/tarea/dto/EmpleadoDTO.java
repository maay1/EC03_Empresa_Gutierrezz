package com.idat.tarea.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoDTO {
	
	private Long idEmpleado;
	@NotBlank(message = "El nombre no puede estar vacío")
	private String nombre;
	@NotBlank(message = "El cargo no puede estar vacío")
	private String cargo;
	private String documento;
	private String estado;
	private Long areaId;	
}
