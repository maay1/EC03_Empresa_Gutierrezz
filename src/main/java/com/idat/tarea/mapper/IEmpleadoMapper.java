package com.idat.tarea.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.idat.tarea.dto.EmpleadoDTO;
import com.idat.tarea.entity.EmpleadoEntity;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapper {
	
	// convertir dto a entity
	EmpleadoEntity toEntity(EmpleadoDTO dto);
	List<EmpleadoEntity> toEntityList(List<EmpleadoDTO> lista);
	
	// convertir entity a dto
	@Mapping(target = "areaId", source = "area.idArea")
	EmpleadoDTO toDTO(EmpleadoEntity entity);
	List<EmpleadoDTO> toDTOList(List<EmpleadoEntity> lista);
}
