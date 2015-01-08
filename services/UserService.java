package DATA.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import DATA.exceptions.BadInformationException;
import DATA.model.Group;
import DATA.model.User;

/**
 * Service for user fonctionalities
 * 
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
	 * 
	 * @param u
	 *            The user to check
	 * @return The user with correct information
	 * @throws BadInformationException
	 *             When incorrect information found
	 */
	public User createUser(User u) throws BadInformationException {
		if (u == null) {
			throw new BadInformationException("User is null");
		}
		if (!checkCredentialNotEmpty(u.getLogin(), u.getPassword())) {
			throw new BadInformationException("Login/password empty");
		}
		/*
		 * if (u.getListIP() == null || u.getListIP().isEmpty()) { throw new
		 * BadInformationException("Password is empty"); }
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
	 * 
	 * @param username
	 *            Username for the user
	 * @param password
	 *            Password for the user
	 * @return The user with correct information
	 * @throws BadInformationException
	 *             When incorrect information found
	 */
	public User checkProfile(String username, String password)
			throws BadInformationException {
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
				&& password.equals(DataService.getInstance().getUser()
						.getPassword())) {
			return DataService.getInstance().getUser();
		}
		return null;
	}

	/**
	 * Check if the credentials are not empty
	 * 
	 * @param username
	 *            Username for the user
	 * @param password
	 *            Password for the user
	 * @return boolean to check not null credentials
	 * @throws BadInformationException
	 *             When incorrect information found
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

	/**
	 * 
	 * @throws IOException
	 */
	public void export_() throws IOException {
		DataService.getInstance().exports();
	}

	/**
	 * 
	 * @param parameter
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public User import_(String parameter) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		File f = new File(parameter);
		DataService.getInstance().imports(f);
		return DataService.getInstance().getUser();
	}

	/**
	 * 
	 * @return
	 */
	public User getCurrentUser() {
		return DataService.getInstance().getUser();
	}

	public boolean updateProfile(User u) throws IOException, BadInformationException {
		User currentUser = DataService.getInstance().getUser();
		if (u == null || u.equals("")) {
			throw new BadInformationException("User empty");
		}
		if (!checkCredentialNotEmpty(u.getLogin(), u.getPassword())) {
			throw new BadInformationException("Login/password empty");
		}
		if (u.getFirstname() == null || u.getFirstname().equals("")) {
			throw new BadInformationException("Firstname empty");
		}
		if (u.getLastname() == null || u.getLastname().equals("")) {
			throw new BadInformationException("Lastname empty");
		}
		if (u.getAvatar() == null || u.getAvatar().equals("")) {
			throw new BadInformationException("Avatar empty");
		}
		if (u.getBirthDate() == null || u.getBirthDate().equals("")) {
			throw new BadInformationException("BirthDate empty");
		}

		currentUser.setLogin(u.getLogin());
		currentUser.setPassword(u.getPassword());
		currentUser.setFirstname(u.getFirstname());
		currentUser.setLastname(u.getLastname());
		currentUser.setAvatar(u.getAvatar());
		currentUser.setBirthDate(u.getBirthDate());

		return DataService.getInstance().setUser(currentUser);
	}

	public void setConnectedUsers(List<String> connectedUsers) {
		DataService.getInstance().getUser().setListConnectedUser(connectedUsers);
	}
	
	public List<String> getConnectedUsers() {
		return DataService.getInstance().getUser().getListConnectedUser();
	}

	/**
	 * Return the user list for a given group
	 * @param g		The group
	 * @return		The user list from the group g
	 * 				Null if the group is not find
	 */
	public List<User> getUserInGroup(Group g) {
		for(Group userGroup : DataService.getInstance().getUser().getListGroups()) {
			if(userGroup.equals(g)) {
				return userGroup.getUsers();
			}
		}
		return null;
	}
}
