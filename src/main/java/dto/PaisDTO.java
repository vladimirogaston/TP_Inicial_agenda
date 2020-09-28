package dto;

public class PaisDTO {

	Integer id;
	
	String nombre;
	
	public PaisDTO(String nombre) {
		super();
		this.nombre = nombre;
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
