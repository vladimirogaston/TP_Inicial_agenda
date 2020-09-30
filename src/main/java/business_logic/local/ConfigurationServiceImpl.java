package business_logic.local;

import java.io.IOException;
import java.util.HashMap;

import business_logic.ConfigurationService;
import dto.ConfigDatabaseDTO;
import repositories.PropertiesServiceImpl;

public class ConfigurationServiceImpl implements ConfigurationService {

	private PropertiesServiceImpl service;
	
	public ConfigurationServiceImpl() {
		this.service = new PropertiesServiceImpl("conf/db.properties");
	}
	
	@Override
	public void save(ConfigDatabaseDTO target) {
		HashMap<String, String> params = new HashMap<>();
		params.put("user", target.getUser());
		params.put("password", target.getPassword());
		params.put("port", target.getPort());
		params.put("ip", target.getIp());
		try {
			service.updateValues(params);
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
