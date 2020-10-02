package dto;

public class TipoContactoDTO {

	Integer id;
	String nombre;

	public TipoContactoDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public TipoContactoDTO(Integer id, String nombre) {
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
}
