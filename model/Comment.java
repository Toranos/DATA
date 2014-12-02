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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (CommentUser == null) {
			if (other.CommentUser != null)
				return false;
		} else if (!CommentUser.equals(other.CommentUser))
			return false;
		if (PictureId == null) {
			if (other.PictureId != null)
				return false;
		} else if (!PictureId.equals(other.PictureId))
			return false;
		if (PictureUserId == null) {
			if (other.PictureUserId != null)
				return false;
		} else if (!PictureUserId.equals(other.PictureUserId))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
