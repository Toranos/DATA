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
		if (u == null) {
			throw new BadInformationException("User is null");
		}
		if (!checkCredentialNotEmpty(u.getLogin(), u.getPassword())) {
			throw new BadInformationException("Login/password empty");
		}
		/*
		if (u.getListIP() == null || u.getListIP().isEmpty()) {
			throw new BadInformationException("Password is empty");
		}
		*/
		data.setUser(u);
		return u;
	} 
	
	/**
	 * Check if the user information is correct for sign in
	 * @param username Username for the user
	 * @param password Password for the user
	 * @return	The user with correct information
	 * @throws BadInformationException	When incorrect information found
	 */
	public User checkProfile(String username, String password) throws BadInformationException {
		if (!checkCredentialNotEmpty(username, password)) {
			throw new BadInformationException("Login/password empty");
		}
		if (username.equals(data.getUser().getLogin())
		&& password.equals(data.getUser().getPassword())) {
			return data.getUser();
		}
		return null;
	}
		
	/**
	 * Check if the credentials are not empty
	 * @param username Username for the user
	 * @param password Password for the user
	 * @return boolean to check not null credentials
	 * @throws BadInformationException	When incorrect information found
	 */
	public boolean checkCredentialNotEmpty(String username, String password) {
		if (username == null || username.equals("")) {
			return false;
		}
		if (password == null || password.equals("")) {
			return false;
		}
		return true;
	}

}
