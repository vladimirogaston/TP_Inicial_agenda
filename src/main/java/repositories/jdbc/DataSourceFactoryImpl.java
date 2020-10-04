package repositories.jdbc;

public class DataSourceFactoryImpl extends DataSourceFactory {

	@Override
	public DataSource makeDataSource(DataSourceType type) {
		DataSource ret = null;
		if(type == DataSourceType.IN_MEMORY) {
			ret = new H2DataSource();
		} else if(type == DataSourceType.PERSISTENT){
			ret = new MySqlDataSource()
					.dbName("grupo_11")
					.user("root")
					.pass("root")
					.host("localhost")
					.port("3306")
					.driver("com.mysql.jdbc.Driver");
		}
		return ret;
	}
}
