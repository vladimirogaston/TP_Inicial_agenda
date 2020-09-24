package persistencia.dao.mysql;

import java.util.Date;

public class Persona {
	
	Integer idPersona;

	String nombre;

	String telefono;

	String email;

	Date fechaNacimiento;

	Integer tipoContactoID;

	String calle;

	String altura;

	String piso;

	String dpto;
	
	Integer localidadID;

	Integer provinciaID;
	
	Integer paisID;
	
	String equipoFutbol;
	
	Integer codigoPostal;

	public Persona() {}
	
	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getTipoContactoID() {
		return tipoContactoID;
	}

	public void setTipoContactoID(Integer tipoContactoID) {
		this.tipoContactoID = tipoContactoID;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public Integer getLocalidadID() {
		return localidadID;
	}

	public void setLocalidadID(Integer localidadID) {
		this.localidadID = localidadID;
	}

	public Integer getProvinciaID() {
		return provinciaID;
	}

	public void setProvinciaID(Integer provinciaID) {
		this.provinciaID = provinciaID;
	}

	public Integer getPaisID() {
		return paisID;
	}

	public void setPaisID(Integer paisID) {
		this.paisID = paisID;
	}

	public String getEquipoFutbol() {
		return equipoFutbol;
	}

	public void setEquipoFutbol(String equipoFutbol) {
		this.equipoFutbol = equipoFutbol;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
}
