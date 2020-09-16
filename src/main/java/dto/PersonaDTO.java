package dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PersonaDTO 
{
	int idPersona;
	
	@NotBlank
	String nombre;

	@NotBlank
	@Digits(fraction = 0, integer = 10)
	String telefono;
	
	@Email
	String email;
	
	Date fechaNacimiento;
	
	@NotBlank
	String tipoContacto;
	
	String calle;
	
	@Digits(fraction = 0, integer = 5)
	String altura;
	
	@Digits(fraction = 0, integer = 2)
	String piso;
	
	String dpto;
	
	String localidad;
	
	public PersonaDTO() {
		super();
	}

	public static PersonaDTO makeTestDto() {
		return new Builder("John", "333")
				.email("john@qm.com")
				.fechaNacimiento(new Date())
				.tipoContacto("Friend")
				.calle("c1")
				.altura("1")
				.piso("1")
				.dpto("A")
				.localidad("Waco")
				.build();
	}
	
	public static class Builder {
		PersonaDTO dto;
		
		public Builder(String nom, String tel) {
			dto = new PersonaDTO();
			dto.nombre = nom;
			dto.telefono = tel;
		}
		
		public Builder id(int id) {
			dto.idPersona = id;
			return this;
		}
		
		public Builder email(String email) {
			dto.email =  email;
			return this;
		}
		
		public Builder fechaNacimiento(Date date) {
			dto.fechaNacimiento = date;
			return this;
		}
		
		public Builder tipoContacto(String tc) {
			dto.tipoContacto = tc;
			return this;
		}
		
		public Builder calle(String calle) {
			dto.calle = calle;
			return this;
		}
		
		public Builder altura(String altura) {
			dto.altura = altura;
			return this;
		}
		
		public Builder piso(String piso) {
			dto.piso = piso;
			return this;
		}
		
		public Builder dpto(String dpto) {
			dto.dpto = dpto;
			return this;
		}
		
		public Builder localidad(String loc) {
			dto.localidad = loc;
			return this;
		}
		
		public PersonaDTO build() {
			return dto;
		}
	}

	public int getIdPersona() {
		return idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public String getCalle() {
		return calle;
	}

	public String getAltura() {
		return altura;
	}

	public String getPiso() {
		return piso;
	}

	public String getDpto() {
		return dpto;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
}
