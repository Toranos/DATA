package DATA.exceptions;

/**
 * Exception for adding picture that already existed.
 * @author yoanns
 *
 */
public class PictureAlreadyExisted extends Exception {

	/**
	 * Unique ID for serialization
	 */
	private static final long serialVersionUID = 1012994167734109247L;
	
	/**
	 * Constructor with message
	 * @param message	Message to be returned
	 */
	public PictureAlreadyExisted(String message){
		super(message);
	}
}
