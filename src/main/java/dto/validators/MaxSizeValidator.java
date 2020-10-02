package dto.validators;

import java.util.LinkedList;
import java.util.List;

public class MaxSizeValidator implements Validator<String> {

	private int max;
	private String message;
	
	public MaxSizeValidator(int max, String message) {
		this.max = max;
		this.message = message;
	}
	
	@Override
	public List<String> validate(String target) {
		List<String> errors = new LinkedList<>();
		if(target.length() > max) errors.add(message);
		return errors;
	}
}
