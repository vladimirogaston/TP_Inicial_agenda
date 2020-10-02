package dto;

import java.util.List;

import dto.validators.StringValidator;

public class PaisDTO {

	Integer id;
	
	String nombre;
	
	public PaisDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public List<String> validate() {
		return new StringValidator(nombre)
				.max(20, "Max 20 chars")
				.notBlank("El nombre del pais no puede estar en blanco")
				.validate();
	}
	
	public PaisDTO(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "PaisDTO [id=" + id + ", nombre=" + nombre + "]";
	}
}
