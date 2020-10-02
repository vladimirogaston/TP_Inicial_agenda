package dto.validators;

import java.util.LinkedList;
import java.util.List;

public class NotNullValidator implements Validator<String> {

	private String message;
	
	public NotNullValidator(String message) {
		this.message = message;
	}
	
	@Override
	public List<String> validate(String t) {
		List<String> errors = new LinkedList<>();
		if(t == null) errors.add(message);
		return errors;
	}
}
