package business_logic.local;

import java.io.IOException;

import org.apache.commons.configuration.PropertiesConfiguration.PropertiesReader;

import business_logic.ConfigurationService;
import dto.ConfigDatabaseDTO;
import repositories.PropertiesServiceImpl;

public class ConfigurationServiceImpl implements ConfigurationService {

	private PropertiesServiceImpl service;
	
	public ConfigurationServiceImpl() {
		this.service = new PropertiesServiceImpl("application.properties");
	}
	
	@Override
	public void save(ConfigDatabaseDTO target) {
		String user = target.getUser();
		String password = target.getPassword();
		String port = target.getPort();
		String ip = target.getIp();
		try {
			this.service.updateValue("user", user);
			this.service.updateValue("password", password);
			this.service.updateValue("port", port);
			this.service.updateValue("ip", ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean test(ConfigDatabaseDTO target) {
		return false;
	}

	@Override
	public ConfigDatabaseDTO getConfiguration() {
		ConfigDatabaseDTO ret = null;
		try {
			String user = service.readProperty("user");
			String password = service.readProperty("password");
			String port = service.readProperty("port");
			String ip = service.readProperty("ip");
			ret = new ConfigDatabaseDTO(user, password, port, ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
