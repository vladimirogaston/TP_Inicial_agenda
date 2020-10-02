package dto.validators;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator implements Validator<String> {

	private String message;
	private String pattern;
	
	public RegexValidator(String message, String regxp) {
		this.message = message;
		this.pattern = regxp;
	}

	@Override
	public List<String> validate(String target) {
		List<String> errors = new LinkedList<>();
		if (!verify(target)) errors.add(message);
		return errors;
	}

	private boolean verify(String target) {
		boolean ret = false;
		Pattern pattern = Pattern.compile(this.pattern);
		Matcher mather = pattern.matcher(target);
		if (mather.find() == true) ret = true;
		return ret;
	}
}
