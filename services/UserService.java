package DATA.services;

import DATA.exceptions.BadInformationException;
import DATA.model.User;

/**
 * Service for user fonctionalities
 * @author le-goc
 *
 */
public class UserService {
	
	private DataService data;
	
	/**
	 * Public constructor for UserService
	 */
	public UserService() {
		this.data = DataService.getInstance();
	}
	
	/**
	 * Check if the user information is correct for sign up
	 * @param u	The user to check
	 * @return	The user with correct information
	 * @throws BadInformationException	When incorrect information found
	 */
	public User createUser(User u) throws BadInformationException {
		if(u == null)
			throw new BadInformationException("User is null");
		if(!checkCredentialNotEmpty(u.getLogin(), u.getPassword()))
			throw new BadInformationException("Login/password empty");
		if(u.getListIP() == null || u.getListIP().isEmpty())
			throw new BadInformationException("Password is empty");
		return u;
	}
	
	public boolean checkCredentialNotEmpty(String login, String password) {
		if(login == null || login.equals("")){
			return false;
		}
		if(password == null || password.equals("")){
			return false;
		}
		return true;
	}

}
