package com.idat.tarea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.tarea.entity.AreaEntity;
import com.idat.tarea.entity.EmpleadoEntity;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long>{
	
	List<EmpleadoEntity> findByNombre(String nombre);
	List<EmpleadoEntity> findByDocumento(String documento);
	List<EmpleadoEntity> findByArea(AreaEntity area);
}
