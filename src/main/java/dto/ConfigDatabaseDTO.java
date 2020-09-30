package dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ConfigDatabaseDTO {

	@NotBlank(message  = "El nombre de usuario es obligatorio.")
	private String user;
	
	@NotBlank(message  = "El password de usuario es obligatorio.")
	private String password;
	
	@NotBlank(message  = "El port es obligatorio.")
	@Pattern(regexp = Patterns.NON_NEGATIVE_INTEGER_FIELD, message = "El port debe ser un número.")	
	private String port;
	
	@NotBlank(message  = "El ip es obligatorio.")
	@Pattern(regexp = Patterns.NON_NEGATIVE_INTEGER_FIELD, message = "El ip debe ser un número.")	
	private String ip;
	
	public ConfigDatabaseDTO(String user, String password, String port, String ip) {
		super();
		setUser(user);
		setPassword(password);
		setPort(port);
		setIp(ip);
	}
	
	public List<String> validate() {
		return GenericValidator.getInstance().validate(this);
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}	
}
