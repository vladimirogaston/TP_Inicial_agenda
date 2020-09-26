package business_logic;

import java.util.List;

public interface Controller<T> {
	
	boolean save(T entity);
	
	boolean update(T entity);
	
	void delete(int id);
	
	List<T> readAll();
}
