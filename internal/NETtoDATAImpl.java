/**
 * 
 */
package DATA.internal;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
		pictureService.addComment(comment);
	}
	
	public void addNote(Note note) {
		pictureService.addNote(note);
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#getConnectedIps()
	 */
	@Override
	public List<String> getConnectedIps() {
		// TODO Auto-generated method stub
		return null;
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
		if (groupService.checkUserPending(user) != null) {
			netLocalizer.addFriend(user.getUid());
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<Picture> getPictures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Picture> getPictures(List<Tag> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Picture getPictureById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
