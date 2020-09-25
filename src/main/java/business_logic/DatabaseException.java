package business_logic;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {
	
	public DatabaseException(String msg) {
		super(msg);
	}
}
