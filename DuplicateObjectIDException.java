/**
 * @author Matthew Garmon
 * @author Walter Conway
 */
@SuppressWarnings("serial")
public class DuplicateObjectIDException extends Exception {
	public DuplicateObjectIDException() {}

	/**
	 * @param message
	 */
	public DuplicateObjectIDException(String message) {
		super(message);
	}
}