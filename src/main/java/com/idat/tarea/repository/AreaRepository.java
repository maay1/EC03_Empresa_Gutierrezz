package com.idat.tarea.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.tarea.entity.AreaEntity;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Long>{
	
}
