package dto;

public class Patterns {
	
	public static final String EMAIL = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
	public static final String DNI = "\\d{8}[A-HJ-NP-TV-Z]";
	
	public static final String TEXT_FIELD = "^(\\S)(.){1,75}(\\S)$";
	
	public static final String NON_NEGATIVE_INTEGER_FIELD = "(\\d){1,9}";
}
