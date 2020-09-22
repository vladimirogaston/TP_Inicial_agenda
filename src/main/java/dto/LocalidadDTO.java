package dto;

public class LocalidadDTO {

	int id;
	String nombre;
	String provincia;	
		
	public LocalidadDTO(int id, String nombre, String provincia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.provincia = provincia;
	}

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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
}
