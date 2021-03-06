package dto;

public class LocalidadDTO {

	Integer id;
	String nombre;
	String provincia;	
		
	public LocalidadDTO(Integer id, String nombre, String provincia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.provincia = provincia;
	}

	public LocalidadDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public LocalidadDTO(Integer id, String nombre) {
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "LocalidadDTO [id=" + id + ", nombre=" + nombre + ", provincia=" + provincia + "]";
	}
}
