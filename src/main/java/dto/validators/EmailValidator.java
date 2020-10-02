package dto.validators;

import java.util.List;

public class EmailValidator implements Validator<String> {

	private String message;
	static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator(String message) {
		this.message = message;
	}

	@Override
	public List<String> validate(String target) {
		return new RegexValidator(message,EMAIL_PATTERN).validate(target);
	}
}
