package com.idat.tarea.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.tarea.entity.AreaEntity;
import com.idat.tarea.repository.AreaRepository;
import com.idat.tarea.services.AreaService;

@Service
public class AreaServicesImpl implements AreaService{

	@Autowired
    private AreaRepository areaRepository;
	
	@Override

	public List<AreaEntity> findAll() {
		return areaRepository.findAll();
	}

	@Override
	public Optional<AreaEntity> findById(Long id) {
		return areaRepository.findById(id);
	}

	@Override
	public AreaEntity save(AreaEntity area) {
		return areaRepository.save(area);
	}

	@Override
	public AreaEntity update(AreaEntity area) {
		return areaRepository.save(area);
	}

	@Override
	public void deleteById(Long id) {
		Optional<AreaEntity> area = areaRepository.findById(id);
        if (area.isPresent()) {
            AreaEntity areaToDelete = area.get();
            areaToDelete.setEstado("I");
            areaRepository.save(areaToDelete);
        } else {
            throw new RuntimeException("Área no encontrada con ID: " + id);
        }	
	}
}
