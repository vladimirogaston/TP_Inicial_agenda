package business_logic;

import java.util.List;

public interface Controller<T> extends SaveUpdateController<T> {
		
	void delete(int id);
	
	List<T> readAll();
}
