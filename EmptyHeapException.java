
/**
 * @author Matthew Garmon
 * @author Walter Conway
 */
@SuppressWarnings("serial")
public class EmptyHeapException extends Exception{

	/**
	 * 
	 */
	public EmptyHeapException(){}
	
	/**
	 * @param message
	 */
	public EmptyHeapException(String message){
		super(message);
	}
	
	
}
