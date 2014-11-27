/**
 * 
 */
package DATA.internal;

import java.util.Iterator;
import java.util.List;

import DATA.interfaces.NETtoDATA;
import DATA.model.Comment;
import DATA.model.Note;
import DATA.model.Picture;
import DATA.model.User;
import DATA.services.DataService;

/**
 * @author le-goc
 *
 */
public class NETtoDATAImpl implements NETtoDATA {
	
	/**
	 * Instance of DataService.
	 */
	private DataService data = null;
	
	/** 
	 * Constructor.
	 */
	public NETtoDATAImpl() {
		data = DataService.getInstance();
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#addComment(DATA.model.Comment)
	 */
	@Override
	public void addComment(Comment comment) {
		User currentUser = data.getUser();
		Iterator<Picture> iter = currentUser.getListPictures().iterator();
	    while (iter.hasNext()) {
	    	if (iter.next().getUid() == comment.getPictureId()) {
	    		iter.next().getComments().add(comment);
	    	}
	    }
	}
	
	public void addNote(Note note) {
		User currentUser = data.getUser();
		Iterator<Picture> iter = currentUser.getListPictures().iterator();
	    while (iter.hasNext()) {
	    	if (iter.next().getUid() == note.getPictureId()) {
	    		iter.next().getListNotes().add(note);
	    	}
	    }
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#getConnectedIps()
	 */
	@Override
	public List<Inet4Address> getConnectedIps() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#getProfil()
	 */
	@Override
	public User getProfil() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#receiveFriendResponse(DATA.model.User)
	 */
	@Override
	public void receiveFriendResponse(User user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#resultPictures(java.util.List, int)
	 */
	@Override
	public void resultPictures(List<Picture> pictures, int idRequest) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#sendPicture(DATA.model.Picture, int)
	 */
	@Override
	public void sendPicture(Picture picture, int pageId) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#helloUser(DATA.model.User)
	 */
	@Override
	public void helloUser(User user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DATA.interfaces.NETtoDATA#goodByeUser(DATA.model.User)
	 */
	@Override
	public void goodByeUser(User user) {
		// TODO Auto-generated method stub

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

}
