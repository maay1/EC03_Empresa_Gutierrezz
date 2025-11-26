package com.idat.tarea.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.idat.tarea.dto.AreaDTO;
import com.idat.tarea.entity.AreaEntity;

@Mapper( componentModel = "spring" )
public interface IAreaMapper {

	// convertir dto a entity
	AreaEntity toEntity(AreaDTO dto);
	List<AreaEntity> toEntityList(List<AreaDTO> lista);
	
	// convertir entity a dto
	AreaDTO toDTO(AreaEntity entity);
	List<AreaDTO> toDTOlist(List<AreaEntity> lista);
}
