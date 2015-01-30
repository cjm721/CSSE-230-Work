/** package weiss.util; */
public class ConcurrentModificationException extends RuntimeException {
	/**
	 * Constructs a ConcurrentModificationException with no detail message.
	 */
	public ConcurrentModificationException() {
		// empty
	}

	/**
	 * Constructs a ConcurrentModificationException with a detail message.
	 * 
	 * @param msg
	 *            the detailed message pertaining to this exception.
	 */
	public ConcurrentModificationException(String msg) {
		super(msg);
	}
}