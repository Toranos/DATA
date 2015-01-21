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
    private User CommentUser;
    private UUID PictureId;
    private UUID PictureUserId;

    /**
     * 
     * @param value
     * @param dateTime
     * @param CommentUser
     * @param PictureId
     * @param PictureUserId
     */
    public Comment(String value, Date dateTime, User CommentUser, UUID PictureId, UUID PictureUserId) {
		this.value = value;
		this.dateTime = dateTime;
		this.uid = UUID.randomUUID();
		this.CommentUser = CommentUser;
		this.PictureId = PictureId;
		this.PictureUserId = PictureUserId;
	}
    
    /**
     * Constructor by copy
     * @param c	The comment to copy
     */
    public Comment(Comment c){
    	this.value = c.value;
    	this.dateTime = c.dateTime;
    	this.uid = c.uid;
    	this.CommentUser = new User(c.CommentUser);
    	this.PictureId = c.PictureId;
    	this.PictureUserId = c.PictureUserId;
    }

	public UUID getPictureId() {
		return PictureId;
	}

	public void setPictureId(UUID pictureId) {
		PictureId = pictureId;
	}

	public UUID getPictureUserId() {
		return PictureUserId;
	}

	public void setPictureUserId(UUID pictureUserId) {
		PictureUserId = pictureUserId;
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

	public User getCommentUser() {
		return CommentUser;
	}

	public void setCommentUser(User commentUser) {
		CommentUser = commentUser;
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
		Comment other = (Comment) obj;
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
	
}
