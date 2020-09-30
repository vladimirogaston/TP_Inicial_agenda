package repositories;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesServiceImpl {

	private String propertiesFileName;
	
	public PropertiesServiceImpl(String propertyFileName) {
		this.propertiesFileName = propertyFileName;
	}
	
	public void updateValues(Map<String, String> values) throws IOException {
		Properties properties = readProperties();
		values.forEach((k,v) -> {
			properties.setProperty(k, v);
		});
		properties.store(new FileOutputStream(propertiesFileName), null);
	}
	
	public String readProperty(String propertyName) throws IOException {
		return readProperties().getProperty(propertyName);
	}

	public Properties readProperties() throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = loader.getResourceAsStream(propertiesFileName);
		Properties properties = new Properties();
		properties.load(inputStream);
		return properties;
	}
}
