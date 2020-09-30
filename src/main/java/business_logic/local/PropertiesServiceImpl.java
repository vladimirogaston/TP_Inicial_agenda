package business_logic.local;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesServiceImpl {

	private String propertiesFileName;
	
	public PropertiesServiceImpl(String propertyFileName) {
		this.propertiesFileName = propertyFileName;
	}
	
	public void updateValue(String propertyName, String value) throws IOException {
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration(propertiesFileName);
			conf.setProperty(propertyName, value);
			conf.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public String readProperty(String propertyName) throws IOException {
		return readProperties(this.propertiesFileName).getProperty(propertyName);
	}

	public Properties readProperties(String file) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = loader.getResourceAsStream(file);
		Properties properties = new Properties();
		properties.load(inputStream);
		return properties;
	}
}
