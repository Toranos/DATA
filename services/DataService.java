package DATA.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javafx.application.Application;
import DATA.exceptions.BadInformationException;
import DATA.model.User;
import IHM.Main;

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
	 * Interval of saving in seconds.
	 */
	private static final long savingInterval = 300;
	
	/**
	 * Image repository.
	 */
	private static final String imageDir = "img";
	
	/**
	 * Filename of export 
	 */
	private static final String exportFile = "export.zip";
	
	/**
	 * Path of database file relative to the current application path. 
	 */
	private static final String profile = "profile.data";
	
	/**
	 * Size of buffer for Zip.
	 */
	private static final int bufferZip = 2048;	

	/**
	 * Singleton variable.
	 */
	private static DataService data = null;
	
	/**
	 * Local and current User.
	 */
	private User user = null;
	
	/**
	 * Current user path for profile and image.
	 */
	private File userPath = null;
	
	/**
	 * Timer for avoiding overload of saving.
	 */
	private Timer time = null;
	
	/**
	 * Variable checked if save is enabled or not. 
	 */
	private boolean enabled = true;
	
	public static void main(String[] args) {
		DataService d = DataService.getInstance();
		d.setPathUser(new File(/*System.getProperty("user.dir")*/"./"));
		System.out.println(d.getPathUser().getCanonicalPath());
	}
	
	/**
	 * Return Singleton instance of DataService.
	 * @return DataService.
	 */
	protected static DataService getInstance() {
		if (data == null) {
			data = new DataService();
		}
		return data;
	}
	
	/**
	 * Private constructor for Singleton Design.
	 */
	private DataService() {
		time = new Timer();
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
	 * Return path user. 
	 * @return File
	 */
	public File getPathUser() {
		return userPath;
	}
	
	/**
	 * Define path user. 
	 * @return User
	 */
	public void setPathUser(File p) {
		userPath = p;
	}

	/**
	 * Save data into file.
	 * @throws IOException 
	 */
	public void save() throws IOException {
		if (enabled) {
			enabled = false;
			ObjectOutputStream oos = null;
			final FileOutputStream file = new FileOutputStream(profile);
			oos = new ObjectOutputStream(file);
			data.time = null;
			oos.writeObject(data);
			oos.flush();
			oos.close();
			Date nextSavingTime = new Date();
			nextSavingTime.setTime(nextSavingTime.getTime() + DataService.savingInterval * 1000);
       	 	time = new Timer();
       	 	time.schedule (new TimerTask() {
       	 		public void run(){
       	 			enabled = true;
       	 			time.cancel();
       	 		}
       	 	}, nextSavingTime);
		}
	}
	
	/**
	 * Force save data into file.
	 * @throws IOException 
	 */
	public void forceSave() throws IOException {
		ObjectOutputStream oos = null;
		final FileOutputStream file = new FileOutputStream(profile);
		oos = new ObjectOutputStream(file);
		data.time = null;
		oos.writeObject(data);
		oos.flush();
		oos.close();
		time.cancel();
		time = null;
	}
	
	/**
	 * Load data from local file.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void load() throws IOException, ClassNotFoundException {
		File file =  new File(profile) ;
		ObjectInputStream ois = null;
		ois = new ObjectInputStream(new FileInputStream(file));
		data = (DataService) ois.readObject();
		ois.close();
	}
	
	/**
	 * Import data from local file.
	 * @param f File File to load.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void imports(File file) throws IOException, ClassNotFoundException {
		
		// Check if the file existe or is readable.
		if (file.exists() == false || file.canRead() == false) {
			throw new FileNotFoundException();
		}
		
		// If the image dir does not exists, we create it.
		File imageDir = new File(DataService.imageDir);
		if (imageDir.exists() == false) {
			imageDir.mkdirs();
		}
		
		// Initialize Zip.
		byte data[] = new byte[bufferZip];
		FileOutputStream destDir = null;
		BufferedOutputStream dest = new BufferedOutputStream(destDir);
		FileInputStream fis = new FileInputStream(file.getPath());
		BufferedInputStream buffi = new BufferedInputStream(fis);
		ZipInputStream zis = new ZipInputStream(buffi);
		ZipEntry entry;
		int nbEntries = 0;
		
		// Get file.
		while((entry = zis.getNextEntry()) != null) {
			FileOutputStream fos = new FileOutputStream(entry.getName());
			dest = new BufferedOutputStream(fos, bufferZip);
			int count;
			while ((count = zis.read(data, 0, bufferZip)) != -1) {
				dest.write(data, 0, count);
			}
			dest.flush();
			dest.close();
			nbEntries++;
		}
		zis.close();
		
		// If file isn't Zip or is empty, throw exception.
		if (nbEntries == 0) {
			throw new IOException();
		}
	}

	/**
	 * Save all information into one zip file.
	 */
	public void exports() throws IOException {
		File profile = new File(System.getProperty("user.dir")+File.separatorChar+DataService.profile);
		
		// If the profile does not exists, export is stopped.
		if (profile.exists() == false) {
			throw new IOException();
		}
		
		// Intitialize Zip.
		byte data[] = new byte[bufferZip];
		FileOutputStream dest = new FileOutputStream(exportFile);
		BufferedOutputStream buff = new BufferedOutputStream(dest);
		ZipOutputStream out = new ZipOutputStream(buff);
		out.setMethod(ZipOutputStream.DEFLATED);
		out.setLevel(9);
		
		// Add profile
		FileInputStream fi = new FileInputStream(profile);
	    BufferedInputStream buffi = new BufferedInputStream(fi, bufferZip);
	    ZipEntry entry = new ZipEntry(profile.getName());
	    out.putNextEntry(entry);
	    int count;
	    while((count = buffi.read(data, 0, bufferZip)) != -1) {
	        out.write(data, 0, count);
	    }
	    out.closeEntry();
	    buffi.close();
		
		// Get all images.
		File[] img = new File(System.getProperty("user.dir")+File.separatorChar+DataService.imageDir).listFiles();
		if (img != null && img.length > 0) {
			
			// Add all images.
			for(File f : img) {
			    fi = new FileInputStream(f);
			    buffi = new BufferedInputStream(fi, bufferZip);
			    entry = new ZipEntry(DataService.imageDir+File.separatorChar+f.getName());
			    out.putNextEntry(entry);
			    while((count = buffi.read(data, 0, bufferZip)) != -1) {
			        out.write(data, 0, count);
			    }
			    out.closeEntry();
			    buffi.close();
			}
		}
		out.close();		
	}
}
