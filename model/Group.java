/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author le-goc
 */
public class Group implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	
    private String nom;
    private List<User> users;
    private UUID uid;
    
    public Group(String nom) {
		this.nom = nom;
		this.users = new ArrayList<User>();
		this.uid = UUID.randomUUID();
	}
    
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

	public UUID getUid() {
		return uid;
	}
}
