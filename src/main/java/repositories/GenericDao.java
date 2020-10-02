package repositories;

import java.util.List;

public interface GenericDao<T, ID> {
	
	boolean update(T entity);

	boolean insert(T entity);
	
	boolean deleteById(ID id);
	
	T readByID(ID id);
	
	List<T> readAll();
}
