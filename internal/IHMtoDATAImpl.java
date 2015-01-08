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
import DATA.model.PendingRequest;
import DATA.model.Picture;
import DATA.model.Tag;
import DATA.model.User;
import DATA.services.DataService;
import DATA.services.GroupService;
import DATA.services.PictureService;
import DATA.services.UserService;
import IHM.Main;
import IHM.interfaces.DATAtoIHM;
import NET.NetLocalizer;
import NET.exceptions.BusinessException;
import NET.exceptions.TechnicalException;

/**
 * @author le-goc
 *
 */
public class IHMtoDATAImpl implements IHMtoDATA {


	private UserService userService;
	private GroupService groupService;
	private PictureService pictureService;
	private DATAtoIHM dataToIhm;
	private NetLocalizer netLocalizer;
	
	/** 
	 * Constructor.
	 */
	public IHMtoDATAImpl() {
		userService = new UserService();
		groupService = new GroupService();
		pictureService = new PictureService();
		dataToIhm = Main.getDATAtoIHMimpl();
		netLocalizer = new NetLocalizer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#addComment(DATA.model.Comment, int)
	 */
	@Override
	public void addComment(Comment comment) throws BadInformationException {
		if (comment == null || comment.equals("")) {
			throw new BadInformationException("Comment empty");
		}
		if (comment.getUid() == null || comment.getUid().equals("")) {
			throw new BadInformationException("Uid empty");
		}
		if (comment.getCommentUser().getUid() == null || comment.getCommentUser().getUid().equals("")) {
			throw new BadInformationException("CommentUserId empty");
		}
		if (comment.getPictureId() == null || comment.getPictureId().equals("")) {
			throw new BadInformationException("PictureId empty");
		}
		if (comment.getPictureUserId() == null || comment.getPictureUserId().equals("")) {
			throw new BadInformationException("PictureUserId empty");
		}
		
		if (pictureService.addComment(comment) == false) {
			try {
				netLocalizer.addComment(comment, comment.getPictureUserId());
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void addNote(Note note) throws BadInformationException {
		if (note == null || note.equals("")) {
			throw new BadInformationException("Note empty");
		}
		if (note.getUid() == null || note.getUid().equals("")) {
			throw new BadInformationException("Uid empty");
		}
		if (note.getNoteUser() == null || note.getNoteUser().equals("")) {
			throw new BadInformationException("NoteUserId empty");
		}
		if (note.getPictureId() == null || note.getPictureId().equals("")) {
			throw new BadInformationException("PictureId empty");
		}
		if (note.getPictureUserId() == null || note.getPictureUserId().equals("")) {
			throw new BadInformationException("PictureUserId empty");
		}
		
		if (pictureService.addNote(note) == false) {
			try {
				netLocalizer.addNote(note, note.getPictureUserId());
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#addGroup(DATA.model.Group)
	 */
	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#addPicture(DATA.model.Picture)
	 */
	@Override
	public void addPicture(Picture picture) {
		pictureService.addPicture(picture);
		try {
			userService.export_();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#addUserInGroup(DATA.model.User,
	 * DATA.model.Group)
	 */
	@Override
	public void addUserInGroup(User user, Group group) {
		if(groupService.addUserInGroup(user, group)) {
			netLocalizer.addFriend(user.getUid());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see DATA.interfaces.IHMtoDATA#deleteComment(DATA.model.Comment)
	 */
	@Override
	public void deleteComment(Comment comment) throws BadInformationException {
		if (comment == null || comment.equals("")) {
			throw new BadInformationException("Comment empty");
		}
		if (comment.getUid() == null || comment.getUid().equals("")) {
			throw new BadInformationException("Uid empty");
		}
		if (comment.getCommentUser().getUid() == null || comment.getCommentUser().getUid().equals("")) {
			throw new BadInformationException("CommentUserId empty");
		}
		if (comment.getPictureId() == null || comment.getPictureId().equals("")) {
			throw new BadInformationException("PictureId empty");
		}
		if (comment.getPictureUserId() == null || comment.getPictureUserId().equals("")) {
			throw new BadInformationException("PictureUserId empty");
		}
		
		if (pictureService.deleteComment(comment) == false) {
			try {
				//netLocalizer.deleteComment(comment, comment.getPictureUserId());
			} catch (Exception e){
				e.printStackTrace();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#deleteGroup(DATA.model.Group)
	 */
	@Override
	public void deleteGroup(Group group) {
		groupService.deleteGroup(group);
		try {
			userService.export_();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#deletePicture(DATA.model.Picture)
	 */
	@Override
	public void deletePicture(Picture picture) {
		pictureService.deletePicture(picture);
		try {
			userService.export_();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#deleteUserFromGroup(DATA.model.User,
	 * DATA.model.Group)
	 */
	@Override
	public void deleteUserFromGroup(User user, Group group) {
		groupService.deleteUserFromGroup(user, group);
		if(group.getNom().equals(Group.FRIENDS_GROUP_NAME)) {
			dataToIhm.receiveReloadUserGroups();
			netLocalizer.deleteFriend(user.getUid());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#export()
	 */
	@Override
	public void export() throws IOException {
		userService.export_();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getUserById(java.util.UUID,
	 * java.lang.String)
	 */
	@Override
	public void getUserById(UUID idUser, int idRequest) {
		netLocalizer.getUserDetails(idUser, idRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getUsersInGroup(DATA.model.Group)
	 */
	@Override
	public List<User> getUsersInGroup(Group g) {
		return userService.getUserInGroup(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getUserNotInGroup(DATA.model.Group)
	 */
	@Override
	public List<User> getUserNotInGroup(Group group) {
		return groupService.getUserNotInGroup(group);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getGroups()
	 */
	@Override
	public List<Group> getGroups() {
		return groupService.getGroups();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getGroupsUserNotIn(DATA.model.User)
	 */
	@Override
	public List<Group> getGroupsUserNotIn(User user) {
		return groupService.getGroupsUserNotIn(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getPictureById(java.util.UUID, int)
	 */
	@Override
	public void getPictureById(UUID pictureUid, int idRequest) {
		Picture myPic = pictureService.getPictureById(pictureUid);
		if (myPic != null) {
			dataToIhm.receivePicture(myPic, idRequest);
		} else {
			netLocalizer.getPictureById(pictureUid, idRequest);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getPictures(DATA.model.User, int)
	 */
	@Override
	public void getPictures(User user, int idRequest) {
		if (userService.getCurrentUser().getUid().equals(user.getUid())) {
			dataToIhm.receivePictures(pictureService.getPictures(null), idRequest);
		} else {
			netLocalizer.getPictures(user.getUid(), idRequest);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getPictures(java.util.List, int)
	 */
	@Override
	public void getPictures(List<Tag> listtag, int idRequest) {
		dataToIhm.receivePictures(pictureService.getPictures(listtag), idRequest);
		netLocalizer.getPictures(listtag,idRequest);
	}
	
	@Override
	public void getPicturesByUsers(List<String> listUser, int idRequest) {
		List<User> connectedUsers = netLocalizer.getConnectedUsers();
		dataToIhm.receivePictures(pictureService.getPicturesByUser(listUser), idRequest);
		for(String user : listUser){
			for(User connected : connectedUsers){
				if(connected.getLogin().equals(user)){
					netLocalizer.getPictures(connected.getUid(), idRequest);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getPictures(int)
	 */
	@Override
	public void getPictures(int idRequest) {
		getPictures(userService.getCurrentUser(), idRequest);
		netLocalizer.getPictures(idRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getCurrentUser()
	 */
	@Override
	public User getCurrentUser() {
		return userService.getCurrentUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#import_(java.lang.String)
	 */
	@Override
	public User import_(String parameter) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		return userService.import_(parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#updatePicture(DATA.model.Picture)
	 */
	@Override
	public void updatePicture(Picture picture) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#updateProfile(DATA.model.User)
	 */
	@Override
	public void updateProfile(User u) throws IOException,
			BadInformationException {
		if (userService.updateProfile(u)) {
			userService.export_();
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#signup(DATA.model.User)
	 */
	@Override
	public boolean signup(User u) {
		try {
			u = userService.createUser(u);
		} catch (BadInformationException e) {
			return false;
		}
		return login(u.getLogin(), u.getPassword());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String username, String password) {
		try {
			User u = userService.checkProfile(username, password);
			if (u != null) {
				try {
					netLocalizer.startAndConnectTo(u);
				} catch (BusinessException | TechnicalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		} catch (BadInformationException e) {
			return false;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#getAllUsers(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<Group> getAllUsers() {
		List<Group> groups = new ArrayList<Group>(groupService.getGroups());
		Group connectedUsersGroup = new Group(Group.DEFAULT_GROUP_NAME);
		boolean isInGroup;
		for (User user : netLocalizer.getConnectedUsers()) {
			isInGroup = false;
			for (Group group : groups) {
				for (User userGroup : group.getUsers()) {
					if (userGroup.getUid().equals(user.getUid())) {
						userGroup.setConnected(true);
						isInGroup = true;
					}
				}
			}
			if (!isInGroup) {
				user.setConnected(true);
				connectedUsersGroup.getUsers().add(user);
			}
		}
		groups.add(connectedUsersGroup);
		return groups;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DATA.interfaces.IHMtoDATA#logout()
	 */
	@Override
	public boolean logout() throws IOException {
		try {
			for (Group group : groupService.getGroups()) {
				for (User userGroup : group.getUsers()) {
					userGroup.setConnected(false);
				}
			}
			userService.setConnectedUsers(netLocalizer.disconnect());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userService.export_();
		return true;
	}

	@Override
	public void acceptUserInGroup(User user, Group group) {
		groupService.acceptUser(user, group);
		try {
			userService.export_();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		netLocalizer.acceptOrNotFriendship(user.getUid(), true);
	}

	@Override
	public void refuseUser(User user) {
		netLocalizer.acceptOrNotFriendship(user.getUid(), false);
	}

	@Override
	public Group getGroupByName(String name) {
		return groupService.getGroup(name);
	}
}
