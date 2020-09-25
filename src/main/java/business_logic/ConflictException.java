package business_logic;

@SuppressWarnings("serial")
public class ConflictException extends RuntimeException {
	
	public ConflictException(String msg) {
		super(msg);
	}
}
