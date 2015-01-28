/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

/**
 * 
 * @author le-goc
 */
public class User implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	
    private UUID uid;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private byte[] avatar;
    private String birthDate;
    private Boolean connected = false;
    private List<Picture> listPictures;
    private List<Group> listGroups;
    private Group otherGroup;
    private List<String> listIP;
    private List<String> listConnectedUser;
    private List<PendingRequest> listPendingRequests;
    
    public User() {
		this.uid = UUID.randomUUID();
		this.listConnectedUser = new ArrayList<String>();
		this.listGroups = new ArrayList<Group>();
		this.listGroups.add(new Group(Group.FRIENDS_GROUP_NAME));
		this.otherGroup = new Group(Group.DEFAULT_GROUP_NAME);
		this.listIP = new ArrayList<String>();
		this.listPictures = new ArrayList<Picture>();
		this.listPendingRequests = new ArrayList<PendingRequest>();
	}
    
    public User(String login, String password) {
    	this.login = login;
		this.password = password;
		this.uid = UUID.randomUUID();
		this.listConnectedUser = new ArrayList<String>();
		this.listGroups = new ArrayList<Group>();
		this.listGroups.add(new Group(Group.FRIENDS_GROUP_NAME));
		this.otherGroup = new Group(Group.DEFAULT_GROUP_NAME);
		this.listIP = new ArrayList<String>();
		this.listPictures = new ArrayList<Picture>();
		this.listPendingRequests = new ArrayList<PendingRequest>();
    }
    
	public User(String login, String password, String firstname,
			String lastname, byte[] avatar, String birthDate) {
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.avatar = avatar;
		this.birthDate = birthDate;
		this.uid = UUID.randomUUID();
		this.listConnectedUser = new ArrayList<String>();
		this.listGroups = new ArrayList<Group>();
		this.listGroups.add(new Group(Group.FRIENDS_GROUP_NAME));
		this.otherGroup = new Group(Group.DEFAULT_GROUP_NAME);
		this.listIP = new ArrayList<String>();
		this.listPictures = new ArrayList<Picture>();
		this.listPendingRequests = new ArrayList<PendingRequest>();
	}
	
	public User(String login, String password, String firstname,
			String lastname, String useless, String birthDate) {
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.uid = UUID.randomUUID();
		this.listConnectedUser = new ArrayList<String>();
		this.listGroups = new ArrayList<Group>();
		this.listGroups.add(new Group(Group.FRIENDS_GROUP_NAME));
		this.otherGroup = new Group(Group.DEFAULT_GROUP_NAME);
		this.listIP = new ArrayList<String>();
		this.listPictures = new ArrayList<Picture>();
		this.listPendingRequests = new ArrayList<PendingRequest>();
	}
	
	public User(User u){
		this.login = u.login;
		this.password = u.password;
		this.uid = u.uid;
		this.birthDate = u.birthDate;
		this.firstname = u.firstname;
		this.lastname = u.lastname;
	}
	
	public UUID getUid() {
		return uid;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public List<Picture> getListPictures() {
		return listPictures;
	}
	public void setListPictures(List<Picture> listPictures) {
		this.listPictures = listPictures;
	}
	public List<Group> getListGroups() {
		return listGroups;
	}
	public void setListGroups(List<Group> listGroups) {
		this.listGroups = listGroups;
	}
	public List<String> getListIP() {
		return listIP;
	}
	public void setListIP(List<String> listIP) {
		this.listIP = listIP;
	}
	public List<String> getListConnectedUser() {
		return listConnectedUser;
	}
	public void setListConnectedUser(List<String> listConnectedUser) {
		this.listConnectedUser = listConnectedUser;
	}
	public List<PendingRequest> getListPendingRequests() {
		return listPendingRequests;
	}
	public void setListPendingRequests(List<PendingRequest> listPendingRequests) {
		this.listPendingRequests = listPendingRequests;
	}	
	public Group getOtherGroup() {
		return otherGroup;
	}
	
	/**
	 * Create a lightUser (UID, login, firstname and lastname) from a complete User
	 * The other fields are null
	 * @return The light user
	 */
	public User getLightUser() {
		User temp = new User();
		temp.uid = this.uid;
		temp.login = this.login;
		temp.firstname = this.firstname;
		temp.lastname = this.lastname;
		temp.avatar = this.avatar;
		return temp;
	}
	
	/**
	 * Return a String from a user : firstname + space + lastname
	 * @return String : firstname + space + lastname
	 */
	@Override
	public String toString() {
		return this.firstname + " " + this.lastname;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = true;
		if (obj == null) {
			isEqual = false;
		}
		User other = (User) obj;
		if (!uid.equals(other.uid)){
			isEqual = false;
		}
		return isEqual;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return 0;
	}
	
	/**
	 * Getter the Avatar in Image object 
	 * @return Image
	 */
	 public Image getAvatarImageObject() {
		 	if(avatar != null) {
		        ByteArrayInputStream in = new ByteArrayInputStream(avatar);
		        try
		        {
		            BufferedImage read = ImageIO.read(in);
		            return SwingFXUtils.toFXImage(read, null);
		        } catch (IOException e)
		        {
		        	Logger.getLogger(User.class.getName()).log(Level.SEVERE, "Error in returning SwingFXUtils.toFXImage(read, null);");
		        }
		 	}
	        return null;
	 }
}
