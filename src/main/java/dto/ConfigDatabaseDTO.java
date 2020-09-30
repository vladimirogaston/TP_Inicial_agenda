package dto;

import javax.validation.constraints.NotBlank;

public class ConfigDatabaseDTO {

	@NotBlank(message  = "El nombre de usuario es obligatorio.")
	private String user;
	
	private String password;
	
	private String port;
	
	private String ip;
	
	public ConfigDatabaseDTO(String user, String password, String port, String ip) {
		super();
		setUser(user);
		setPassword(password);
		setPort(port);
		setIp(ip);
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
