package business_logic.local;

import java.io.IOException;
import java.util.HashMap;

import business_logic.ConfigurationService;
import dto.ConfigDatabaseDTO;
import repositories.PropertiesServiceImpl;
import repositories.jdbc.Conexion;
import repositories.jdbc.DatabaseException;

public class ConfigurationServiceImpl implements ConfigurationService {

	private PropertiesServiceImpl service;
	
	public ConfigurationServiceImpl() {
		this.service = new PropertiesServiceImpl("conf\\db.properties");
	}
	
	@Override
	public void save(ConfigDatabaseDTO target) {
		HashMap<String, String> params = new HashMap<>();
		params.put("user", target.getUser());
		params.put("password", target.getPassword());
		params.put("port", target.getPort());
		params.put("ip", target.isLocalhost() ? "localhost" : target.getIp());
		try {
			service.updateValues(params);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onConnect(ConfigDatabaseDTO target) {
		try {
			Conexion.getConexion(target).getSQLConexion();
			return true;
		} catch(DatabaseException e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	@Override
	public ConfigDatabaseDTO getConfiguration() {
		ConfigDatabaseDTO ret = null;
		try {
			ret = new ConfigDatabaseDTO()
					.ip(service.readProperty("ip"))
					.port(service.readProperty("port"))
					.user(service.readProperty("user"))
					.password(service.readProperty("password"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean isConnectionEnabled() {
		ConfigDatabaseDTO target = getConfiguration();
		if(onConnect(target)) return true;
		return false;
	}
}
