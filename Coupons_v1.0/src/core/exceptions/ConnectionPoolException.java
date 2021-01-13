package core.exceptions;

/**
 * ConnectionPoolException is an exception used to indicates exception which
 * derived from the connection pool methods.
 */
public class ConnectionPoolException extends CouponsException {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}

}
