package com.idat.tarea.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "area_emp")
public class AreaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_area")
	private Long idArea;
	
	@Column(name = "nombre", length= 100, nullable= false, unique = true)
	private String nombre;
	
	@Column(name = "descripcion", length= 255, nullable= true)
	private String descripcion;
	
	@Column(name = "estado", length= 1, nullable= false)
	private String estado;
	
	@OneToMany(mappedBy= "area", cascade = CascadeType.ALL)
	private List<EmpleadoEntity> empleado;
	
	public AreaEntity() {}

	public AreaEntity(Long idArea, String nombre, String descripcion, String estado) {
		super();
		this.idArea = idArea;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long id) {
		this.idArea = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "AreaEntity [idArea=" + idArea + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado
				+ "]";
	}
	
	
	
	
}
