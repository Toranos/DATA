/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private String avatar;
    private String birthDate;
    private List<Picture> listPictures;
    private List<Group> listGroups;
    private List<Inet4Address> listIP;
    private List<Inet4Address> listConnectedUser;
    private List<PendingRequest> listPendingRequests;
    
    public User() {
    	
	}
    
	public User(String login, String password, String firstname,
			String lastname, String avatar, String birthDate) {
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.avatar = avatar;
		this.birthDate = birthDate;
		this.uid = UUID.randomUUID();
		this.listConnectedUser = new ArrayList<Inet4Address>();
		this.listGroups = new ArrayList<Group>();
		this.listIP = new ArrayList<Inet4Address>();
		this.listPictures = new ArrayList<Picture>();
		this.listPendingRequests = new ArrayList<PendingRequest>();
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
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
	public List<Inet4Address> getListIP() {
		return listIP;
	}
	public void setListIP(List<Inet4Address> listIP) {
		this.listIP = listIP;
	}
	public List<Inet4Address> getListConnectedUser() {
		return listConnectedUser;
	}
	public void setListConnectedUser(List<Inet4Address> listConnectedUser) {
		this.listConnectedUser = listConnectedUser;
	}
	public List<PendingRequest> getListPendingRequests() {
		return listPendingRequests;
	}
	public void setListPendingRequests(List<PendingRequest> listPendingRequests) {
		this.listPendingRequests = listPendingRequests;
	}
}
