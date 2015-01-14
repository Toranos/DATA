/**
 * 
 */
package DATA.internal;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import DATA.exceptions.BadInformationException;
import DATA.interfaces.NETtoDATA;
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
import IHM.interfaces.DATAtoIHMimpl;
import NET.NetLocalizer;
/**
 * @author le-goc
 *
 */
public class NETtoDATAImpl implements NETtoDATA {
	
	private UserService userService;
	private GroupService groupService;
	private PictureService pictureService;
	private DATAtoIHM dataToIhm;
	private NetLocalizer netLocalizer;
	
	/** 
	 * Constructor.
	 */
	public NETtoDATAImpl() {
		userService = new UserService();
		groupService = new GroupService();
		pictureService = new PictureService();
		dataToIhm = Main.getDATAtoIHMimpl();
		netLocalizer = new NetLocalizer();
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#addComment(DATA.model.Comment)
	 */
	@Override
	public void addComment(Comment comment) {
		try{
			pictureService.addComment(comment);
		} catch (BadInformationException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#addNote(DATA.model.Note)
	 */
	@Override
	public void addNote(Note note) {
		try{
			pictureService.addNote(note);
		} catch (BadInformationException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#deleteComment(DATA.model.Comment)
	 */
	@Override
	public void deleteComment(Comment comment){
		try{
			pictureService.deleteComment(comment);
		} catch (BadInformationException e){
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#getConnectedIps()
	 */
	@Override
	public List<String> getConnectedIps() {
		return userService.getConnectedUsers();
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#getProfil()
	 */
	@Override
	public User getProfil() {
		return userService.getCurrentUser();
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#infoUser(DATA.model.User, int)
	 */
	@Override
	public void infoUser(User user, int idRequest) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#receiveFriendRequest(DATA.model.User)
	 */
	@Override
	public void receiveFriendRequest(User user) {
		dataToIhm.receiveFriendRequest(user);
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#receiveFriendResponse(DATA.model.User)
	 */
	@Override
	public void receiveFriendResponse(User user, boolean friends) {
		groupService.receiveFriendResponse(user,friends);
		dataToIhm.receiveFriendResponse(user,friends);
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#receiveFriendResponse(DATA.model.User)
	 */
	@Override
	public void receiveCommentResponse(User user, Comment comment) {
		pictureService.receiveCommentResponse(user,comment);
	}
	
	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#receiveFriendResponse(DATA.model.User)
	 */
	@Override
	public void receiveNoteResponse(User user, Note note) {
		pictureService.receiveNoteResponse(user,note);
	}
	
	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#resultPictures(java.util.List, int)
	 */
	@Override
	public void resultPictures(List<Picture> pictures, int idRequest) {
		dataToIhm.receivePictures(pictures, idRequest);
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#sendPicture(DATA.model.Picture, int)
	 */
	@Override
	public void sendPicture(Picture picture, int idRequest) {
		dataToIhm.receivePicture(picture, idRequest);
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#helloUser(DATA.model.User)
	 */
	@Override
	public void helloUser(User user) {
		user.setConnected(true);
		dataToIhm.receiveConnectedUser(user);
		for (PendingRequest pendingReq : groupService.getUserPending(user)) {
			switch (pendingReq.getType()) {
			case PendingRequest.ASK_FRIEND:
				netLocalizer.addFriend(user.getUid());
				break;
			case PendingRequest.ASK_UNFRIEND:
				netLocalizer.deleteFriend(user.getUid());		
				break;
			case PendingRequest.SEND_COMMENT:
				netLocalizer.addComment(pendingReq.getComment(), user.getUid());
				break;
			case PendingRequest.SEND_NOTE:
				netLocalizer.addNote(pendingReq.getNote(), user.getUid());
				break;
			default:
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#goodByeUser(DATA.model.User)
	 */
	@Override
	public void goodByeUser(User user) {
		user.setConnected(false);
		dataToIhm.receiveUnconnectedUser(user);
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#notFriendAnymore(DATA.model.User)
	 */
	@Override
	public void notFriendAnymore(User user) {
		groupService.deleteUserFromGroup(user, groupService.getGroup(Group.FRIENDS_GROUP_NAME));
		dataToIhm.receiveReloadUserGroups();
	}

	@Override
	public List<Picture> getPictures() {
		return pictureService.getPictures(null);
	}

	@Override
	public List<Picture> getPictures(List<Tag> tags) {
		return pictureService.getPictures(tags);
	}

	@Override
	public Picture getPictureById(UUID id) {
		return pictureService.getPictureById(id);
	}
}
