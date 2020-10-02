package dto.validators;

import java.util.LinkedList;
import java.util.List;

public class NotBlankValidator implements Validator<String> {

	private String message;
	
	public NotBlankValidator(String message) {
		this.message = message;
	}
	
	@Override
	public List<String> validate(String target) {
		List<String> errors = new LinkedList<>();
		if(target.trim().isEmpty()) errors.add(message);
		return errors;
	}
}