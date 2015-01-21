package DATA.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import DATA.exceptions.BadInformationException;
import DATA.model.Group;
import DATA.model.Picture;
import DATA.model.User;

/**
 * Service for user fonctionalities
 * 
 * @author le-goc
 *
 */
public class UserService {
	private static String separator = File.separator;
	private static String rootFile = "data" + separator;
	private static String rootName = "data";
	private static String accountFile = "accounts.data";

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
		
		try {
			createAccountsFile(u);
		} catch(IOException e){
			e.printStackTrace();
		}
		
		// Création du dossier utilisateur
		Path path = Paths.get(rootFile + u.getUid());
		if(!Files.exists(path)){
			boolean success = (new File(rootFile + u.getUid())).mkdirs();
			if (!success) {
			    System.out.println("Erreur lors de la creation du dossier /data/uid");
			}
		}
		
		DataService.getInstance().setUser(u);
		DataService.getInstance().setPathUser(new File(rootFile + u.getUid() + File.separator));
		try {
			DataService.getInstance().save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur lors de la création du fichier de data");
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
		
		// Verification de l'existence dans accounts
		try {
			BufferedReader br = new BufferedReader(new FileReader(rootFile + accountFile));
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" ");
				if(parts[0].equals(username) && parts[1].equals(password)){
					System.out.println("Real : " + username + " - find : " + parts[0]);
					DataService.getInstance().setPathUser(new File(rootFile + parts[2]));
					DataService.load();
					return DataService.getInstance().getUser();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		DataService.imports(f);
		return DataService.getInstance().getUser();
	}
	
	/**
	 * Save the profile in the data file, end the time scheduler for saving
	 * @throws IOException 
	 */
	public void forceSave() throws IOException{
		DataService.getInstance().forceSave();
	}
	
	/**
	 * Save the profile in the data file
	 * @throws IOException
	 */
	public void save() throws IOException {
		DataService.getInstance().save();
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
		if (u == null) {
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
		
		// Modify the line in accounts.data
		String toUpdate = currentUser.getLogin() + " " + currentUser.getPassword() + " " + currentUser.getUid();
		String updated = u.getLogin() + " " + u.getPassword() + " " + currentUser.getUid();
		updateLine(toUpdate, updated);
		
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
	
	public void createAccountsFile(User u) throws IOException{
		// Création du dossier data
		Path path = Paths.get(rootName);
		if(!Files.exists(path)){
			boolean success = (new File(rootName)).mkdirs();
			if (!success) {
			    System.out.println("Erreur lors de la creation du dossier /data");
			}
		}
		
		//Creation du fichier accounts.data
		File accounts = new File(rootFile + accountFile);
		if(!accounts.exists()){
			PrintWriter writer = new PrintWriter(accounts);
			writer.println(u.getLogin() + " " + u.getPassword() + " " + u.getUid());
			writer.close();
		} else {
			PrintWriter out = new PrintWriter(new FileWriter(accounts, true));
			out.append(System.lineSeparator() + u.getLogin() + " " + u.getPassword() + " " + u.getUid());
			out.close();
		}
	}
	
	private void updateLine(String toUpdate, String updated) throws IOException {
	    BufferedReader file = new BufferedReader(new FileReader(rootFile + accountFile));
	    String line;
	    String input = "";

	    while ((line = file.readLine()) != null)
	        input += line + System.lineSeparator();

	    input = input.replace(toUpdate, updated);

	    FileOutputStream os = new FileOutputStream(rootFile + accountFile);
	    os.write(input.getBytes());

	    file.close();
	    os.close();
	}
}
