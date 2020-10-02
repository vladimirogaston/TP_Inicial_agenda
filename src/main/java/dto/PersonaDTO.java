package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonaDTO {
	
	Integer idPersona;

	@Size(max = 60, message = "Maximo 60 caracteres para el código nombre.")
	@NotBlank(message = "El nombre es obliatorio.")
	String nombre;

	@Size(max = 12, message = "Maximo 12 digitos para el teléfono.")
	@NotBlank(message = "El teléfono es obligatorio.")
	String telefono;

	@Size(max = 60, message = "Maximo 60 caracteres para el email.")
	@Email(message = "El email es debe ser un email.")
	String email;

	@Past(message = "La fecha debe ser menor a la actual.")
	Date fechaNacimiento;

	@NotBlank(message = "El tipo de contacto es obligatorio.")
	String tipoContacto;

	String calle;

	@Size(max = 6, message = "Maximo 6 digitos para la altura.")
	@Pattern(regexp = Patterns.NON_NEGATIVE_INTEGER_FIELD, message = "La altura debe ser un número.")
	String altura;

	@Size(max = 3, message = "Maximo 3 digitos para la piso.")
	@Pattern(regexp = Patterns.NON_NEGATIVE_INTEGER_FIELD, message = "El piso debe ser un número.")	
	String piso;

	@Size(max = 4, message = "Maximo 4 digitos para el dpto.")
	String dpto;
	
	String localidad;

	String provincia;
	
	String pais;
	
	String equipoFutbol;
	
	@Size(max = 6, message = "Maximo 6 digitos para el código postal.")
	@Pattern(regexp = Patterns.NON_NEGATIVE_INTEGER_FIELD, message = "El codigo postal debe ser un número.")
	String codigoPostal;
	
	public PersonaDTO() {
		super();
	}
	
	public List<String> validate() {
		ArrayList<String> properties = new ArrayList<>();
		properties.addAll(mandatoryPropertiesToValidate());
		properties.addAll(optionalPropertiesToValidate());
		String [] params = new String [properties.size()];
		return GenericValidator.getInstance().validate(this, properties.toArray(params));
	}
	
	ArrayList<String> mandatoryPropertiesToValidate() {
		ArrayList<String> properties = new ArrayList<>();
		properties.add("nombre");
		properties.add("telefono");
		return properties;
	}
	
	ArrayList<String> optionalPropertiesToValidate() {
		ArrayList<String> properties = new ArrayList<String>();
		if(!email.isEmpty()) properties.add("email");
		if(fechaNacimiento != null) properties.add("fechaNacimiento");
		if(!calle.isEmpty()) properties.add("calle");
		if(!altura.isEmpty()) properties.add("altura");
		if(!piso.isEmpty()) properties.add("piso");
		if(!dpto.isEmpty()) properties.add("dpto");
		if(!altura.isEmpty()) properties.add("localidad");
		if(!tipoContacto.isEmpty()) properties.add("tipoContacto");
		if(!codigoPostal.isEmpty()) properties.add("codigoPostal");
		return properties;
	}

	public static PersonaDTO makeTestDto() {
		return new Builder("John", "333").email("john@qm.com").fechaNacimiento(new Date()).tipoContacto("Friend")
				.calle("c1").altura("1").piso("1").dpto("A").localidad("Waco").build();
	}

	public static class Builder {
		PersonaDTO dto;

		public Builder(String nom, String tel) {
			dto = new PersonaDTO();
			dto.setNombre(nom);
			dto.setTelefono(tel);
		}

		public Builder id(Integer id) {
			dto.setIdPersona(id);
			return this;
		}

		public Builder provincia(String provincia) {
			dto.setProvincia(provincia);
			return this;
		}
		
		public Builder pais(String pais) {
			dto.setPais(pais);
			return this;
		}
		
		public Builder email(String email) {
			dto.setEmail(email);
			return this;
		}

		public Builder fechaNacimiento(Date fechaNacimiento) {
			dto.setFechaNacimiento(fechaNacimiento);
			return this;
		}

		public Builder tipoContacto(String tc) {
			dto.setTipoContacto(tc);
			return this;
		}

		public Builder calle(String calle) {
			dto.setCalle(calle);
			return this;
		}

		public Builder altura(String altura) {
			dto.setAltura(altura);
			return this;
		}

		public Builder piso(String piso) {
			dto.setPiso(piso);
			return this;
		}

		public Builder dpto(String dpto) {
			dto.setDpto(dpto);
			return this;
		}

		public Builder localidad(String loc) {
			dto.setLocalidad(loc);
			return this;
		}

		public Builder equipoFutbol(String eq) {
			dto.setEquipoFutbol(eq);
			return this;
		}
		
		public Builder codigoPostal(String cp) {
			dto.setCodigoPostal(cp);
			return this;
		}
		
		public PersonaDTO build() {
			return dto;
		}
	}

	public Integer getId() {
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

	public void setIdPersona(Integer idPersona) {
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEquipoFutbol() {
		return equipoFutbol;
	}

	public void setEquipoFutbol(String equipoFutbol) {
		this.equipoFutbol = equipoFutbol;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public String toString() {
		return "PersonaDTO [idPersona=" + idPersona + ", nombre=" + nombre + ", telefono=" + telefono + ", email="
				+ email + ", fechaNacimiento=" + fechaNacimiento + ", tipoContacto=" + tipoContacto + ", calle=" + calle
				+ ", altura=" + altura + ", piso=" + piso + ", dpto=" + dpto + ", localidad=" + localidad
				+ ", provincia=" + provincia + ", pais=" + pais + ", equipoFutbol=" + equipoFutbol + ", codigoPostal="
				+ codigoPostal + "]";
	}
	
	public Integer getCodigoPostalInteger() {
		try {
			return Integer.parseInt(codigoPostal);			
		}catch(NumberFormatException e) {
			return null;
		}
	}
}
