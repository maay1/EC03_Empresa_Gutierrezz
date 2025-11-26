package com.idat.tarea.services;

import java.util.List;
import java.util.Optional;

import com.idat.tarea.entity.AreaEntity;
import com.idat.tarea.entity.EmpleadoEntity;

public interface EmpleadoService {

	List<EmpleadoEntity> findAll();
	
	Optional<EmpleadoEntity> findById(Long id);
	//Optional<EmpleadoEntity> findById(Long id);
	
	EmpleadoEntity save(EmpleadoEntity empleado);
	
	EmpleadoEntity update(EmpleadoEntity empleado);
	
	void deleteById(Long id);
	
	List<EmpleadoEntity> findByNombre(String nombre);
	
	List<EmpleadoEntity> findByDocumento(String documento);

}
