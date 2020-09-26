package business_logic.exceptions;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3600783490726024139L;

	public NotFoundException(String message) {
		super(message);
	}
}
