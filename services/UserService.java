package DATA.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import DATA.exceptions.BadInformationException;
import DATA.model.User;

/**
 * Service for user fonctionalities
 * @author le-goc
 *
 */
public class UserService {
	
	/**
	 * Public constructor for UserService
	 */
	public UserService() {
	
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
		DataService.getInstance().setUser(u);
		try {
			DataService.getInstance().exports();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			DataService.imports();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (username.equals(DataService.getInstance().getUser().getLogin())
		&& password.equals(DataService.getInstance().getUser().getPassword())) {
			return DataService.getInstance().getUser();
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
