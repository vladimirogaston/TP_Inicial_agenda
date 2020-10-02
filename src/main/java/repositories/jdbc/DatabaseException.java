package repositories.jdbc;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 2316709483248780258L;

	public DatabaseException(String msg) {
		super(msg);
	}
}
