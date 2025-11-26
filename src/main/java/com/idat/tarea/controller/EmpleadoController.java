package com.idat.tarea.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idat.tarea.dto.EmpleadoDTO;
import com.idat.tarea.entity.AreaEntity;
import com.idat.tarea.entity.EmpleadoEntity;
import com.idat.tarea.exception.InvalidArgumentException;
import com.idat.tarea.exception.NotFoundException;
import com.idat.tarea.mapper.IEmpleadoMapper;
import com.idat.tarea.repository.AreaRepository;
import com.idat.tarea.services.EmpleadoService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/empleados")
@Data
@Slf4j
public class EmpleadoController {

	private final EmpleadoService empleadoService;
	private final IEmpleadoMapper mapperEmpleado;
	private final AreaRepository areaRepo;
	
	
	@GetMapping("/listar")
	public String listarEmpleados(Model model) {
		log.info("listando empleados");
		
        List<EmpleadoEntity> empleados = empleadoService.findAll();
        List<EmpleadoDTO> empleadoDTO = mapperEmpleado.toDTOList(empleados);
        List<AreaEntity> areas = areaRepo.findAll();
        
        model.addAttribute("empleados" ,empleadoDTO);
        model.addAttribute("areas",areas);
        return "empleados/listado";
    }
	
	@GetMapping("/buscar-nombre")
    public String buscarPorNombre(@RequestParam(required = false) String nombre, Model model) {
        log.info("Buscando empleados por nombre: {}", nombre);
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            if (nombre.matches(".*\\d.*")) {
                throw new InvalidArgumentException("El nombre no puede contener números.");
            }
        }
        
        List<EmpleadoEntity> empleados;
        if (nombre == null || nombre.trim().isEmpty()) {
            empleados = empleadoService.findAll();
        } else {
            empleados = empleadoService.findByNombre(nombre.trim());
        }
        List<EmpleadoDTO> empleadoDTO = mapperEmpleado.toDTOList(empleados);
        List<AreaEntity> areas = areaRepo.findAll();
        Map<Long, String> mapaAreas = new HashMap<>();
        for (AreaEntity area : areas) {
            mapaAreas.put(area.getIdArea(), area.getNombre());
        }
        
        model.addAttribute("empleados", empleadoDTO);
        model.addAttribute("nombre", nombre);
        model.addAttribute("areas",areas);
        model.addAttribute("mapaAreas", mapaAreas);
        return "empleados/buscar-nombre";
    }
	
	@GetMapping("/buscar-documento")
    public String buscarPorDocumento(@RequestParam(required = false) String documento, Model model) {
        log.info("Buscando empleados por documento: {}", documento);

        if (documento != null && !documento.trim().isEmpty()) {
            if (!documento.matches("\\d+")) {
                throw new InvalidArgumentException("El documento debe contener solo números.");
            }
        }
        
        List<EmpleadoEntity> empleados;
        if (documento == null || documento.trim().isEmpty()) {
            empleados = empleadoService.findAll();
        } else {
            empleados = empleadoService.findByDocumento(documento.trim());
        }
        List<EmpleadoDTO> empleadoDTO = mapperEmpleado.toDTOList(empleados);
        List<AreaEntity> areas = areaRepo.findAll();
        Map<Long, String> mapaAreas = new HashMap<>();
        for (AreaEntity area : areas) {
            mapaAreas.put(area.getIdArea(), area.getNombre());
        }
        
        model.addAttribute("empleados", empleadoDTO);
        model.addAttribute("documento", documento);
        model.addAttribute("areas",areas);
        model.addAttribute("mapaAreas", mapaAreas);
        return "empleados/buscar-documento";
    }
	
	@GetMapping("/resumen")
	public String mostrarResumen(Model model) {
	    log.info("Mostrando resumen de empleados por área");
	    
	    List<AreaEntity> areas = areaRepo.findAll();
	    List<EmpleadoEntity> empleados = empleadoService.findAll();
	    List<Map<String, Object>> resumen = new ArrayList<>(); 
	    for (AreaEntity area : areas) {
	        long total = empleados.stream()
	            .filter(emp -> emp.getArea() != null && emp.getArea().getIdArea().equals(area.getIdArea()))
	            .count();          
	        long activos = empleados.stream()
	            .filter(emp -> emp.getArea() != null && 
	                          emp.getArea().getIdArea().equals(area.getIdArea()) && 
	                          "A".equals(emp.getEstado()))
	            .count();	            
	        long inactivos = empleados.stream()
	            .filter(emp -> emp.getArea() != null && 
	                          emp.getArea().getIdArea().equals(area.getIdArea()) && 
	                          "I".equals(emp.getEstado()))
	            .count();	        
	        Map<String, Object> areaResumen = new HashMap<>();
	        areaResumen.put("nombreArea", area.getNombre());
	        areaResumen.put("total", total);
	        areaResumen.put("activos", activos);
	        areaResumen.put("inactivos", inactivos);
	        
	        resumen.add(areaResumen);
	    }
	    
	    model.addAttribute("resumen", resumen);
	    return "empleados/resumen";
	}
	
	@GetMapping("/editar/{id}")
	public String editorEmpleado(@PathVariable("id") Long idEmpleado, Model model) {
		log.info("Mostrando formulario para editar empleado ID: {}", idEmpleado);
		if (idEmpleado == null || idEmpleado<= 0) {
            throw new IllegalArgumentException("El id no es valido");
        }
		
        Optional<EmpleadoEntity> empleado = empleadoService.findById(idEmpleado);
        if (empleado.isPresent()) {
            EmpleadoDTO empleadoDTO = mapperEmpleado.toDTO(empleado.get());
            List<AreaEntity> areas = areaRepo.findAll();
            
            model.addAttribute("empleado", empleadoDTO);
            model.addAttribute("areas", areas);
            return "empleados/editar";
        } else {
            return "redirect:/empleados/listar";
        }
    }
	
	@GetMapping("/nuevo")
    public String agregarEmpleado(Model model) {
        log.info("Mostrando formulario para nuevo empleado");
        List<AreaEntity> areas = areaRepo.findAll();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setEstado("A");
        
        model.addAttribute("empleado", empleadoDTO);
        model.addAttribute("areas", areas);
        return "empleados/agregar";
    }
	
	@PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") EmpleadoDTO empleadoDTO, @RequestParam Long areaId) {
		log.info("Guardando nuevo empleado");
		if (empleadoDTO.getNombre() == null || empleadoDTO.getDocumento() == null || empleadoDTO.getCargo() == null) {
            throw new IllegalArgumentException("No debe haber campos vacios");
        }
		if (areaId == null || areaId <= 0) {
	        throw new InvalidArgumentException("Debe seleccionar un área.");
	    }
		if (!empleadoDTO.getDocumento().matches("\\d+")) {
	        throw new InvalidArgumentException("El documento debe contener solo números.");
	    }
		if (empleadoDTO.getDocumento().length() < 8 || empleadoDTO.getDocumento().length() >8) {
	        throw new InvalidArgumentException("El documento debe tener 8 caracteres");
	    }
		
		AreaEntity area = areaRepo.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Área no encontrada"));
		log.info("Guardando nuevo empleado");
		EmpleadoEntity empleadoEntity = mapperEmpleado.toEntity(empleadoDTO);
        empleadoEntity.setArea(area);
        empleadoService.save(empleadoEntity);     
        return "redirect:/empleados/listar";
    }
	
	@PostMapping("/actualizar")
    public String actualizarEmpleado(@ModelAttribute("empleado") EmpleadoDTO empleadoDTO, @RequestParam Long areaId) {
		log.info("Actualizando empleado");
		if (empleadoDTO.getNombre() == null || empleadoDTO.getDocumento() == null || empleadoDTO.getCargo() == null) {
            throw new IllegalArgumentException("No debe haber campos vacios");
        }
		AreaEntity area = areaRepo.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Área no encontrada"));
        
        EmpleadoEntity empleadoEntity = mapperEmpleado.toEntity(empleadoDTO);
        empleadoEntity.setArea(area);
        empleadoService.update(empleadoEntity);     
        return "redirect:/empleados/listar";
    }
	
	 @PostMapping("/eliminar/{id}")
	 public String eliminarEmpleado(@PathVariable Long id) {
		 log.info("Cambienado estado del empleado ID: {}", id);
	     empleadoService.deleteById(id);
	     return "redirect:/empleados/listar";
	    }
	 
	 
}
