package dto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class GenericValidator {

    static final GenericValidator instance = new GenericValidator();

    GenericValidator(){
        super();
    }

    public static GenericValidator getInstance() {
        return instance;
    }

    public <T> String validate(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        StringBuilder str = new StringBuilder();
        for (ConstraintViolation<T> violation : constraintViolations) {
            str.append(violation.getMessage() + "\n");
        }
        return str.toString();
    }
}