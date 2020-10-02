package business_logic;

@SuppressWarnings("serial")
public class ForbiddenException extends RuntimeException {
	
	public ForbiddenException(String msg) {
		super(msg);
	}
}
