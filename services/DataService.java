package DATA.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import DATA.model.User;

/**
 * Main service of data handler.
 * @author Yoann Chaumin
 */
public class DataService implements Serializable {
	
	/**
	 * Serialization UID.
	 */
	public static final long serialVersionUID = 1L;
	
	/**
	 * Path of database file relative to the current application path. 
	 */
	private static String profile = "profile.data";

	/**
	 * Singleton variable.
	 */
	private static DataService data = null;
	
	/**
	 * Local and current User.
	 */
	private User user = null;
	
	/**
	 * Return Singleton instance of DataService.
	 * @return DataService.
	 */
	public static DataService getInstance() {
		if (data == null) {
			data = new DataService();
		}
		return data;
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
		
		return user;
	}
	
	/**
	 * Define current application user. 
	 * @return User
	 */
	public boolean setUser(User u) {
		user = u;
		return true;
	}
	
	/**
	 * Return path profile. 
	 * @return String
	 */
	public String getPathProfile() {
		return profile;
	}
	
	/**
	 * Define path profile. 
	 * @return User
	 */
	public boolean getPathProfile(String p) {
		profile = p;
		return true;
	}

	/**
	 * Save data into file.
	 * @throws IOException 
	 */
	public void exports() throws IOException {
		ObjectOutputStream oos = null;
		final FileOutputStream file = new FileOutputStream(profile);
		oos = new ObjectOutputStream(file);
		oos.writeObject(data);
		oos.flush();
		oos.close();
	}
	
	/**
	 * Load data from local file.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void imports() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file =  new File(profile) ;
		ObjectInputStream ois = null;
		ois = new ObjectInputStream(new FileInputStream(file));
		data = (DataService) ois.readObject();
		ois.close();
	}
	
	/**
	 * Load data from local file.
	 * @param f File File to load.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void imports(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = null;
		ois = new ObjectInputStream(new FileInputStream(file));
		data = (DataService) ois.readObject();
		ois.close();
	}
}
