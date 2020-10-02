package dto.validators;

import java.util.List;

public interface Validator<T> {
	
	List<String> validate(T t);
}
