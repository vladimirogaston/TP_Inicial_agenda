package dto.validators;

import java.util.LinkedList;
import java.util.List;

public class MinSizeValidator implements Validator<String> {

	private int min;
	private String message;
	
	public MinSizeValidator(int min, String message) {
		this.min = min;
		this.message = message;
	}
	
	@Override
	public List<String> validate(String target) {
		List<String> errors = new LinkedList<>();
		if(target.length() < min) errors.add(message);
		return errors;
	}
}