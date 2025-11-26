package com.idat.tarea.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado")
public class EmpleadoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private Long idEmpleado;
	
	@Column(name = "nombre", length= 120, nullable= false)
	private String nombre;
	
	@Column(name = "cargo", length= 50, nullable= false)
	private String cargo;
	
	@Column(name= "documento", length= 8, nullable= false)
	private String documento;
	
	@Column(name = "estado", length=1, nullable= false)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "idArea", nullable = false)
	private AreaEntity area;

	public EmpleadoEntity() {
		super();
	}

	public EmpleadoEntity(Long idEmpleado, String nombre, String cargo,String documento, String estado, AreaEntity area) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.cargo = cargo;
		this.documento = documento;
		this.estado = estado;
		this.area = area;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public AreaEntity getArea() {
		return area;
	}

	public void setArea(AreaEntity area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "EmpleadoEntity [idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", cargo=" + cargo + ",documento=" + documento +", estado="
				+ estado + ", area=" + area + "]";
	}
}
