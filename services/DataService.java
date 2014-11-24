package DATA.services;

import java.io.File;
import java.io.FileInputStream;
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

	public void exports() {
		ObjectOutputStream oos = null;
		try {
			final FileOutputStream file = new FileOutputStream(data.getPathProfile());
			oos = new ObjectOutputStream(file);
			oos.writeObject(data);
			oos.flush();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void imports() {
		File fichier =  new File(profile) ;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichier));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			data = (DataService) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
