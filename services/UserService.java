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
		if(u == null) throw new BadInformationException("This is a null pointer");
		
		if(u.getFirstname() == null || u.getFirstname().equals("")) 
			throw new BadInformationException("First name is empty");
		
		if(u.getLastname() == null || u.getLastname().equals(""))
			throw new BadInformationException("Last name is empty");
		
		if(u.getLogin() == null || u.getLogin().equals(""))
			throw new BadInformationException("Login is empty");
		
		if(u.getPassword() == null || u.getPassword().equals(""))
			throw new BadInformationException("Password is empty");
		return u;
	}

}
