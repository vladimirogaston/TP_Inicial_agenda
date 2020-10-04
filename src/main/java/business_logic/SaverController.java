package business_logic;

public interface SaverController<T> {
	
	boolean save(T entity);
}
