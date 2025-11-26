package com.idat.tarea.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.tarea.entity.AreaEntity;
import com.idat.tarea.entity.EmpleadoEntity;
import com.idat.tarea.repository.EmpleadoRepository;
import com.idat.tarea.services.EmpleadoService;



@Service
public class EmpleadoServicesImpl implements EmpleadoService{

	@Autowired
    private EmpleadoRepository empleadoRepository;

	@Override
	public List<EmpleadoEntity> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public Optional<EmpleadoEntity> findById(Long id) {
		return empleadoRepository.findById(id);
	}

	@Override
	public List<EmpleadoEntity> findByNombre(String nombre) {
		return empleadoRepository.findByNombre(nombre);
	}

	@Override
	public List<EmpleadoEntity> findByDocumento(String documento) {
		return empleadoRepository.findByDocumento(documento);
	}
	
	@Override
	public EmpleadoEntity save(EmpleadoEntity empleado) {
		return empleadoRepository.save(empleado);
	}

	
	@Override
	public void deleteById(Long id) {
		Optional<EmpleadoEntity> empleado = findById(id);
		if(empleado.isPresent()) {
			EmpleadoEntity empleadoToDelete = empleado.get();
			empleadoToDelete.setEstado("I");
			empleadoRepository.save(empleadoToDelete);
		}else {
			throw new RuntimeException("Empleado no encontrada con ID: " + id);
		}
		
	}

	@Override
	public EmpleadoEntity update(EmpleadoEntity empleado) {
		return empleadoRepository.save(empleado);
	}

}
