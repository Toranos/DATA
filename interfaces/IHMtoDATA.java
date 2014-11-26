package DATA.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import DATA.model.Comment;
import DATA.model.Group;
import DATA.model.Note;
import DATA.model.Picture;
import DATA.model.User;

/**
 * Interface call by IHM
 * @author le-goc
 *
 */
public  interface IHMtoDATA 
{
	/**
	 * Add a new comment for a picture 
	 * @param comment	The comment
	 * @param idRequest	The idRequest
	 */
	public void addComment(Comment comment, int idRequest) ;
	
	/**
	 * Add a new comment for a picture 
	 * @param comment	The comment
	 * @param idRequest	The idRequest
	 */
	public void addNote(Note note, int idRequest) ;
	
	/**
	 * Add a new group for the current user
	 * @param group The new group
	 */
	public void addGroup(Group group) ;
	
	/**
	 * Add a picture for the current user
	 * @param picture	The picture
	 */
	public void addPicture(Picture picture) ;
	
	/**
	 * Add a user in a group
	 * @param user	The user to add
	 * @param group	The group
	 */
	public void addUserInGroup(User user, Group group) ;
	
	/**
	 * Delete a group and all its users
	 * @param group	The gorup to delete
	 */
	public void deleteGroup(Group group) ;
	
	/**
	 * Delete a picture and all its comments, notes and tags
	 * @param picture	The picture to delete
	 */
	public void deletePicture(Picture picture) ;
	
	/**
	 * Delete the user from the group specified
	 * @param user : The user to delete
	 * @param group : The group
	 */
	public void deleteUserFromGroup(User user, Group group) ;
	
	/**
	 * Save the current user in a JSON file
	 */
	public void export() throws IOException;
	
	/**
	 * Get the user information
	 * @param idUser	: The user ID
	 * @param idRequest	: The request ID (for IHM)
	 */
	public void getUserById(UUID idUser, String idRequest) ;
	
	/**
	 * Get all the users in the specified group
	 * @return	The user list
	 */
	public List<User> getUsersInGroup(Group g) ;
	
	/**
	* @Brief : Request the list of users who are not in this group
	* @param : Group
	* @return : list of user
	*/
	public List<User> getUserNotInGroup(Group group) ;
	
	/**
	* @Brief Request the Group with the name in param
	* @param name of the group searched
	* @return the Group searched
	*/
	public Group getGroup(String group) ;
	
	/**
	* @Brief : Request all the group of the current user
	* @param : None
	* @return : list of all the group
	*/
	public List<Group> getGroups() ;
	
	/**
	* @Brief : Request all the group where the user is not in
	* @param : user
	* @return : list of this group
	*/
	public List<Group> getGroupsUserNotIn(User user) ;
	
	/**
	* @Brief : Request a picture (with the id) on the network 
	* @param group : id of the picture
	* @return : None (catch exception)
	*/
	public void getPictureById(UUID picture, int idRequest) ;
	
	/**
	 * @Brief Request a user’s pictures on the network
	 * @param user
	 * @param idRequest
	 */
	public void getPictures(User user, int idRequest) ;
	
	/**
	 * @Brief Request a picture from a list of tags
	 * @param listtag
	 * @param idRequest
	 */
	public void getPictures(List<String> listtag, int idRequest) ;
	
	/**
	 * @Brief Request all the pictures from all connected users
	 * @param idRequest
	 */
	public void getPictures(int idRequest) ;
	
	/**
	* @Brief Visit current user’s profile
	* @return User
	*/
	public User getCurrentUser() ;
	
	/**
	* @Brief Import a user from JSON file’s path
	* @param parameter
	* @return User
	 * @throws ClassNotFoundException 
	 */
	public User import_(String parameter) throws IOException, ClassNotFoundException;
	
	/**
	 * @Brief Update current picture
	 * @param picture
	 */
	public void updatePicture(Picture picture) ;
	
	/**
	 * @Brief Update user in the JSON file
	 * @param parameter
	 */
	public void updateProfile(User u) throws IOException;
	
	/**
	 * Sign Up a new user, start the server and connect the user
	 * Required : User with firstname, lastname, login, password and avatar
	 * @param u : A light User with the login information
	 * @return true : signup OK
	 * 		   false : signup KO
	 */
	public boolean signup(User u);
	
	/**
	 * Connect the user, check the password and username
	 * @param username
	 * @param password
	 * @return true : Connect OK
	 * 		 false : Connect KO
	 */
	public boolean login(String username, String password);
	
	/**
	 * List all users in group and connected users
	 * @return List of User's groups with connected users group
	 */
	public List<Group> getAllUsers();
	
	/**
	 * Logout the current user
	 * Save its state
	 * @return true : OK
	 * 			false : KO
	 */
	public boolean logout() throws IOException;
	
	/**
	 * Edit the current profile
	 * @param u : The new light user with the new information
	 * @return	true : edit OK
	 * 			false : edit KO
	 */
	public boolean editProfile(User u);
}

