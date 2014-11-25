/**
 * 
 */
package DATA.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import DATA.exceptions.BadInformationException;
import DATA.interfaces.IHMtoDATA;
import DATA.model.Comment;
import DATA.model.Group;
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

	/* (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#addComment(DATA.model.Comment, int)
	 */
	@Override
	public void addComment(Comment comment, int idRequest) {
		// TODO Auto-generated method stub

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
	public void export() {
		// TODO Auto-generated method stub

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
	public User import_(String parameter) {
		// TODO Auto-generated method stub
		return null;
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
	public void updateProfile(User u) {
		User CurrentUser = DataService.getInstance().getUser();
		CurrentUser.setLogin(u.getLogin());
		CurrentUser.setPassword(u.getPassword());
		CurrentUser.setFirstname(u.getFirstname());
		CurrentUser.setLastname(u.getLastname());
		CurrentUser.setAvatar(u.getAvatar());
		CurrentUser.setBirthDate(u.getBirthDate());
	  
		if (DataService.getInstance().setUser(CurrentUser)){
			DataService.getInstance().exports();
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
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
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
