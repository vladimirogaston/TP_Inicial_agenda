package business_logic;

import dto.ConfigDatabaseDTO;

public interface ConfigurationService {

	void save(ConfigDatabaseDTO target);

	boolean test(ConfigDatabaseDTO target);

	ConfigDatabaseDTO getConfiguration();
}
