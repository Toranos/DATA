package DATA.exceptions;

/**
 * Exception for inofrmation missing during signing up
 * @author le-goc
 *
 */
public class BadInformationException extends Exception {

	/**
	 * Unique ID for serialization
	 */
	private static final long serialVersionUID = 1012994167734109247L;
	
	/**
	 * Constructor with message
	 * @param message	Message to be returned
	 */
	public BadInformationException(String message){
		super(message);
	}

}
