package dto;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class ConfigDatabaseDTO {

	private String user;
	
	private String password;
	
	private String port;
	
	private String ip;
	
	private String driver;
	
	private boolean isLocalhost;
	
	public List<String>	validate() {
		List<String> userErrors = new StringValidator(user).notBlank("El usuario es obligatorio").validate();
		List<String> passwordErrors = new StringValidator(password).notBlank("El password es obligatorio").validate();
		List<String> portErrors = new StringValidator(port)
				.notBlank("El port es obligatorio")
				.regex("El port debe ser un número",Patterns.NON_NEGATIVE_INTEGER_FIELD)
				.validate();
		List<String> ipErrors = new LinkedList<>();
		if(!isLocalhost) {
			ipErrors = new StringValidator(ip).notBlank("El ip es obligatorio").validate();	
		}
		userErrors.addAll(passwordErrors);
		userErrors.addAll(portErrors);
		userErrors.addAll(ipErrors);
		return userErrors;
	}
	
	public ConfigDatabaseDTO() {
		super();
		isLocalhost = false;
	}
	
	public ConfigDatabaseDTO isLocalhost(boolean value) {
		isLocalhost = value;
		return this;
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

	public boolean isLocalhost() {
		return isLocalhost;
	}

	public void setLocalhost(boolean isLocalhost) {
		this.isLocalhost = isLocalhost;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "ConfigDatabaseDTO [user=" + user + ", password=" + password + ", port=" + port + ", ip=" + ip
				+ ", isLocalhost=" + isLocalhost + "]";
	}		
}
