package DATA.interfaces;

import java.util.List;
import java.util.UUID;

import DATA.model.Comment;
import DATA.model.Note;
import DATA.model.Picture;
import DATA.model.Tag;
import DATA.model.User;

public interface NETtoDATA {
	
	/**
	 * Send a comment to a remote picture. 
	 * @param comment
	 */
	public void addComment(Comment comment) ;
	
	/**
	 * Send a comment to a remote picture. 
	 * @param comment
	 */
	public void addNote(Note note) ;
	
	/**
	 * Return the list of saved IPs.
	 * @return List<Inet4Address>
	 */
	public List<String> getConnectedIps() ;
	
	/**
	 * Return current local user. 
	 * @return User
	 */
	public User getProfil() ;
	
	/**
	 * Send information about remote User.
	 * @param user
	 * @param idRequest
	 */
	public void infoUser(User user, int idRequest) ;
	
	/**
	 * Inform that the current User has a friend request.
	 * @param idSender
	 */
	public void receiveFriendRequest(User user) ;
	
	/**
	 * Inform that the current User of friend response.
	 * @param idSender
	 * @param accept TRUE if the remote user had accepted the request.
	 */
	public void receiveFriendResponse(User user, boolean accept) ;
	
	/**
	 * Send list pictures asked by the User. 
	 * @param pictures
	 * @param idRequest
	 */
	public void resultPictures(List<Picture> pictures, int idRequest) ;

	/**
	 * Send picture asked by the User.
	 * @param picture
	 * @param pageId
	 */
	public void sendPicture(Picture picture, int pageId) ;

	/**
	 * Get local pictures with a given ID
	 * @param The picture ID
	 * @return The picture 
	 */
	public Picture getPictureById(UUID id);
	
	/**
	 * Get local pictures for NET
	 * @return The picture List
	 */
	public List<Picture> getPictures() ;
	
	/**
	 * Get local pictures for NET
	 * @param The list of tags
	 * @return The picture List
	 */
	public List<Picture> getPictures(List<Tag> tags);

	/**
	 * Send connected user.
	 * @param user
	 */
	public void helloUser(User user) ;
	
	/**
	 * Inform that user is unconnected.
	 * @param user
	 */
	public void goodByeUser(User user) ;
	
	/**
	 * Inform that your friend remove you to his friend.
	 * @param user
	 */
	public void notFriendAnymore(User user) ;
	
	/**
	 * Inform that request isn't sent and need to be save to send again later. 
	 * @param u
	 */
	public void FriendResquestNotSent(UUID uid) ;
}