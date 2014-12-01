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
    private UUID CommentUserId;
    private UUID PictureId;
    private UUID PictureUserId;

    /**
     * 
     * @param value
     * @param dateTime
     * @param CommentUserId
     * @param PictureId
     * @param PictureUserId
     */
    public Comment(String value, Date dateTime, UUID CommentUserId, UUID PictureId, UUID PictureUserId) {
		this.value = value;
		this.dateTime = dateTime;
		this.uid = UUID.randomUUID();
		this.CommentUserId = CommentUserId;
		this.PictureId = PictureId;
		this.PictureUserId = PictureUserId;
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

	public UUID getCommentUserId() {
		return CommentUserId;
	}

	public void setCommentUserId(UUID commentUserId) {
		CommentUserId = commentUserId;
	}
}
