package DATA.services;

import DATA.model.User;

/**
 * Main service of data handler.
 * @author Yoann Chaumin
 */
public class DataService {
	/**
	 * Path of database file relative to the current application path. 
	 */
	private String pathProfile = "profile.json";

	/**
	 * Singleton variable.
	 */
	private static DataService dataService = null;
	
	/**
	 * Local and current User.
	 */
	private User currentUser = null;
	
	/**
	 * Return Singleton instance of DataService.
	 * @return DataService.
	 */
	public static DataService getInstance() {
		if (dataService == null) {
			dataService = new DataService();
		}
		return dataService;
	}
	
	/**
	 * Private constructor for Singleton Design.
	 */
	private DataService() {
		
	}
	
	/**
	 * Return current application user. 
	 * @return User
	 */
	public User getUser() {
		
		return currentUser;
	}
	
	/**
	 * Define current application user. 
	 * @return User
	 */
	public boolean setUser(User u) {
		currentUser = u;
		return true;
	}
	
	/**
	 * Return path profile. 
	 * @return String
	 */
	public String getPathProfile() {
		return pathProfile;
	}
	
	/**
	 * Define path profile. 
	 * @return User
	 */
	public boolean getPathProfile(String p) {
		pathProfile = p;
		return true;
	}
}
