package DATA.interfaces;

import java.net.Inet4Address;
import java.util.List;

import DATA.model.Comment;
import DATA.model.Picture;
import DATA.model.User;

public interface NETtoDATA {
	
	/**
	 * Send a comment to a remote picture. 
	 * @param comment
	 */
	public void addComment(Comment comment) ;
	
	/**
	 * Return the list of saved IPs.
	 * @return List<Inet4Address>
	 */
	public List<Inet4Address> getConnectedIps() ;
	
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
	 */
	public void receiveFriendResponse(User user) ;
	
	/**
	 * Send list pictures asked by the User. 
	 * @param pictures
	 * @param idRequest
	 */
	public void resultPictures(List<Picture> pictures, int idRequest) ;

	/**
	 * Send picture acked by the User.
	 * @param picture
	 * @param pageId
	 */
	public void sendPicture(Picture picture, int pageId) ;

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
}