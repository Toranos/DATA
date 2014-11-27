/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author le-goc
 */
public class Comment implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	
    private String value;
    private Date dateTime;
    private UUID uid;
    private User user;

    public Comment(String value, Date dateTime, User user) {
		this.value = value;
		this.dateTime = dateTime;
		this.uid = UUID.randomUUID();
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

	public UUID getUid() {
		return uid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
