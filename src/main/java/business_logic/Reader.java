package business_logic;

import java.util.List;

public interface Reader<T> {
	
	List<T> readAll();
}
