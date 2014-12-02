/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author le-goc
 */
public class Note implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	
    private int value;  
    private UUID uid;
    private User NoteUser;
    private UUID PictureId;
    private UUID PictureUserId;
    
	public Note(int value, User NoteUser, UUID PictureId, UUID PictureUserId) {
		this.value = value;
		this.uid = UUID.randomUUID();
		this.NoteUser = NoteUser;
		this.PictureId = PictureId;
		this.PictureUserId = PictureUserId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public UUID getUid() {
		return uid;
	}

	public User getNoteUser() {
		return NoteUser;
	}

	public void setNoteUser(User noteUser) {
		NoteUser = noteUser;
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
}
