/**
 * 
 */
package DATA.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import DATA.exceptions.BadInformationException;
import DATA.interfaces.IHMtoDATA;
import DATA.model.Comment;
import DATA.model.Group;
import DATA.model.Note;
import DATA.model.Picture;
import DATA.model.User;
import DATA.services.DataService;
import DATA.services.UserService;
import NET.NetLocalizer;

/**
 * @author le-goc
 *
 */
public class IHMtoDATAImpl implements IHMtoDATA {

	/**
	 * Instance of DataService.
	 */
	private DataService data = null;
	
	/** 
	 * Constructor.
	 */
	public IHMtoDATAImpl() {
		data = DataService.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#addComment(DATA.model.Comment, int)
	 */
	@Override
	public void addComment(Comment comment) {
		NetLocalizer netLocalizer = new NetLocalizer();
		netLocalizer.addComment(comment, comment.getPictureUserId());
	}
	
	@Override
	public void addNote(Note note) {
		NetLocalizer netLocalizer = new NetLocalizer();
		netLocalizer.addNote(note, note.getPictureUserId());
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#addGroup(DATA.model.Group)
	 */
	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#addPicture(DATA.model.Picture)
	 */
	@Override
	public void addPicture(Picture picture) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#addUserInGroup(DATA.model.User, DATA.model.Group)
	 */
	@Override
	public void addUserInGroup(User user, Group group) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#deleteGroup(DATA.model.Group)
	 */
	@Override
	public void deleteGroup(Group group) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#deletePicture(DATA.model.Picture)
	 */
	@Override
	public void deletePicture(Picture picture) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#deleteUserFromGroup(DATA.model.User, DATA.model.Group)
	 */
	@Override
	public void deleteUserFromGroup(User user, Group group) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#export()
	 */
	@Override
	public void export() throws IOException {
		data.exports();
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getUserById(java.util.UUID, java.lang.String)
	 */
	@Override
	public void getUserById(UUID idUser, String idRequest) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getUsersInGroup(DATA.model.Group)
	 */
	@Override
	public List<User> getUsersInGroup(Group g) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getUserNotInGroup(DATA.model.Group)
	 */
	@Override
	public List<User> getUserNotInGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getGroup(java.lang.String)
	 */
	@Override
	public Group getGroup(String group) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getGroups()
	 */
	@Override
	public List<Group> getGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getGroupsUserNotIn(DATA.model.User)
	 */
	@Override
	public List<Group> getGroupsUserNotIn(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getPictureById(java.util.UUID, int)
	 */
	@Override
	public void getPictureById(UUID picture, int idRequest) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getPictures(DATA.model.User, int)
	 */
	@Override
	public void getPictures(User user, int idRequest) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getPictures(java.util.List, int)
	 */
	@Override
	public void getPictures(List<String> listtag, int idRequest) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getPictures(int)
	 */
	@Override
	public void getPictures(int idRequest) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getCurrentUser()
	 */
	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#import_(java.lang.String)
	 */
	@Override
	public User import_(String parameter) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		File f = new File(parameter);
		data.imports(f);
		return data.getUser();
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#updatePicture(DATA.model.Picture)
	 */
	@Override
	public void updatePicture(Picture picture) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#updateProfile(DATA.model.User)
	 */
	@Override
	public void updateProfile(User u) throws IOException, BadInformationException {
		UserService userService = new UserService();
		User currentUser = data.getUser();
		if (u == null || u.equals("")) {
			throw new BadInformationException("User empty");
		}
		if (!userService.checkCredentialNotEmpty(u.getLogin(), u.getPassword())) {
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
	  
		if (data.setUser(currentUser)){
			data.exports();
		}
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#signup(DATA.model.User)
	 */
	@Override
	public boolean signup(User u) {
		UserService userService = new UserService();
		try {
			u = userService.createUser(u);
		} catch (BadInformationException e){
			return false;
		}
		login(u.getLogin(), u.getPassword());
		return true;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String username, String password) {
		UserService userService = new UserService();
		User u;
		try {
			u = userService.checkProfile(username, password);
			if(u != null) {
				NetLocalizer netLocalizer = new NetLocalizer();
				netLocalizer.startAndConnectTo(u);
				return true;
			}
		} catch (BadInformationException e){
			return false;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#getAllUsers(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Group> getAllUsers() {
		List<Group> groups = new ArrayList<Group>(DataService.getInstance().getUser().getListGroups());
		NetLocalizer netLocalizer = new NetLocalizer();
		Group connectedUsers = new Group("Utilisateurs connectés");
		connectedUsers.setUsers(netLocalizer.getConnectedUsers());
		if (connectedUsers.getUsers() != null 
			&& !connectedUsers.getUsers().isEmpty()) {
			groups.add(connectedUsers);
		}
		return groups;
	}
	
	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#logout()
	 */
	@Override
	public boolean logout() throws IOException {
		DataService.getInstance().exports();
		NetLocalizer netLocalizer = new NetLocalizer();
		netLocalizer.disconnect();
		return true;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#editProfile(DATA.model.User)
	 */
	@Override
	public boolean editProfile(User u) {
		// TODO Auto-generated method stub
		return false;
	}
}
