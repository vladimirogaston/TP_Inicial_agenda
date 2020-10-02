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
	private String ip;
	
	public ConfigDatabaseDTO() {
		super();
	}
	
	public ConfigDatabaseDTO user(String user) {
		setUser(user);
		return this;
	}
	
	public ConfigDatabaseDTO password(String password) {
		setPassword(password);
		return this;
	}
	
	public ConfigDatabaseDTO ip(String ip) {
		setIp(ip);
		return this;
	}
	
	public ConfigDatabaseDTO port(String port) {
		setPort(port);
		return this;
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

	@Override
	public String toString() {
		return "ConfigDatabaseDTO [user=" + user + ", password=" + password + ", port=" + port + ", ip=" + ip + "]";
	}	
}
