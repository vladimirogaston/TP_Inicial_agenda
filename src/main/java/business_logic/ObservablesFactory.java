package business_logic;

public abstract class ObservablesFactory {

	private static ObservablesFactory instance;
	
	public static void setFactory(ObservablesFactory concrete) {
		instance = concrete;
	}
	
	public static ObservablesFactory getFactory() {
		assert instance != null;
		return instance;
	}
	
	public abstract Observable makeObservable(Class<? extends Object> type);
}
