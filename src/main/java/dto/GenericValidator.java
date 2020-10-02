package dto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GenericValidator {

	private Validator validator;
	private static GenericValidator instance;

	protected GenericValidator() {
		super();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public static GenericValidator getInstance() {
		if(instance == null) {
			instance = new GenericValidator();
		}
		return instance;
	}

	public <T> List<String> validate(T entity, String... propertyNames) {
		List<String> errors = new LinkedList<>();
		for(String property : propertyNames) {
			errors.addAll(getErrors(validator.validateProperty(entity, property)));
		}
		return errors;
	}
	
	public <T> List<String> validate(T entity, String propertyName) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(entity, propertyName);
		return getErrors(constraintViolations);
	}
	
	public <T> List<String> validate(T entity) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
		return getErrors(constraintViolations);
	}

	private <T> List<String> getErrors(Set<ConstraintViolation<T>> constraintViolations) {
		List<String> errors = new LinkedList<>();
		constraintViolations.stream().forEach(violation->errors.add(violation.getMessage()));
		return errors;
	}
}