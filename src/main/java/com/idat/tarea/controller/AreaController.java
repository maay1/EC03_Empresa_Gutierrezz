package com.idat.tarea.controller;




import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idat.tarea.dto.AreaDTO;
import com.idat.tarea.entity.AreaEntity;
import com.idat.tarea.mapper.IAreaMapper;
import com.idat.tarea.services.AreaService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/areas")
@Data
@Slf4j
public class AreaController {	
	
	private final AreaService areaService;
	private final IAreaMapper mapperArea;
	
	@GetMapping("/listar")
	public String listarAreas(Model model) {
		log.info("listando areas");
		List<AreaEntity> area = areaService.findAll();
		List<AreaDTO> areaDTO = mapperArea.toDTOlist(area);
		model.addAttribute("areas",areaDTO);
		return "areas/listado";
	}
	
	@GetMapping("/editar/{id}")
	public String editarArea(@PathVariable("id") Long id, Model model) {
		log.info("Mostrando formulario para editar area :", id);
		
		Optional<AreaEntity> area= areaService.findById(id);
		AreaDTO areaDTO= mapperArea.toDTO(area.get());
		model.addAttribute("area", areaDTO);
		return "areas/editar";
	}
	
	@GetMapping("/nuevo")
	public String agregarEmpleado(Model model) {
		log.info("");
		AreaDTO areaDTO = new AreaDTO();
		areaDTO.setEstado("A");	
		model.addAttribute("area", areaDTO);
		return "areas/agregar";
	}
	
	@PostMapping("/guardar")
	public String guardarArea(@ModelAttribute("area") AreaDTO areaDTO) {
		log.info("Guardando nueva area :", areaDTO.getNombre());
		AreaEntity areaEntity = mapperArea.toEntity(areaDTO);
		areaService.save(areaEntity);
		return "redirect:/areas/listar";
		
	}
	
	@PostMapping("/actualizar")
	public String actualizarArea(@ModelAttribute("area") AreaDTO areaDTO) {
		log.info("Actualizando area: ", areaDTO.getIdArea());
		AreaEntity areaEntity = mapperArea.toEntity(areaDTO);
		areaService.update(areaEntity);
		return "redirect:/areas/listar";
	}
	
	@PostMapping("/eliminar/{id}")
	public String eliminarArea(@PathVariable Long id) {
		log.info("Cambiando estado del empleado ID: {}", id);
		areaService.deleteById(id);
		return "redirect:/areas/listar";
	}
	
}
