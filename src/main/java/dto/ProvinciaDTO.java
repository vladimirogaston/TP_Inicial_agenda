package dto;

public class ProvinciaDTO {
	
	Integer id;	
	String nombre;
	String pais;
	
	public ProvinciaDTO(Integer id, String nombre, String pais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
	}
	
	public ProvinciaDTO(Integer id, String nombre) {
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "ProvinciaDTO [id=" + id + ", nombre=" + nombre + ", pais=" + pais + "]";
	}
	
}
