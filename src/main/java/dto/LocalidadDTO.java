package dto;

public class LocalidadDTO {

	int id;
	String nombre;
	
	public LocalidadDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public LocalidadDTO(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
