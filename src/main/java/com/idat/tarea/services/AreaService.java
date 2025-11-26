package com.idat.tarea.services;

import java.util.List;
import java.util.Optional;

import com.idat.tarea.entity.AreaEntity;

public interface AreaService {

	List<AreaEntity> findAll();
	
	Optional<AreaEntity> findById(Long id);
	
	AreaEntity save(AreaEntity area);
	
	AreaEntity update(AreaEntity area);
	
	void deleteById(Long id);

}
