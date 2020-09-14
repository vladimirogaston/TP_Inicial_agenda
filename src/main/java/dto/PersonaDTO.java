package dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PersonaDTO 
{
	int idPersona;
	
	@NotBlank
	String nombre;

	@NotBlank
	@Positive
	String telefono;
	
	@Email
	String email;
	
	Date fechaNacimiento;
	
	@NotBlank
	String tipoContacto;
	
	String calle;
	
	String altura;
	
	String piso;
	
	String dpto;
	
	String localidad;
	
	public PersonaDTO() {
		super();
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
}
