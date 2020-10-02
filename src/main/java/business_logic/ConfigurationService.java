package business_logic;

import dto.ConfigDatabaseDTO;

public interface ConfigurationService {

	void save(ConfigDatabaseDTO target);

	boolean onConnect(ConfigDatabaseDTO target);

	ConfigDatabaseDTO getConfiguration();
	
	boolean isConnectionEnabled();
}
