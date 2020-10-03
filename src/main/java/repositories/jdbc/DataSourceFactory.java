package repositories.jdbc;

public abstract class DataSourceFactory {

	public static enum DataSourceType {
		IN_MEMORY, PERSISTENT
	}
	
	private static DataSourceFactory factory;
	
	public static void setFactory(DataSourceFactory concreteFactory) {
		factory = concreteFactory;
	}
	
	public static DataSourceFactory getFactory() {
		return factory;
	}
	
	public abstract DataSource makeDataSource(DataSourceType type);
}