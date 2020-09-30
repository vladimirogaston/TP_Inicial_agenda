package business_logic;

public interface SaveUpdateController<T> {
	
	boolean save(T entity);
	
	boolean update(T entity);
}
