package DATA.model;

import java.io.Serializable;
import java.util.UUID;

public class PendingRequest implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	public static final int ASK_FRIEND = 1;
	public static final int ASK_UNFRIEND = 2;
	public static final int SEND_COMMENT = 3;
	public static final int SEND_NOTE = 4;
	
	private int type;
	private UUID toUID;
	private UUID groupUID;
	private Comment comment;
	private Note note;
	
	public PendingRequest(UUID toUID) {
		this.toUID = toUID;
		this.type = ASK_UNFRIEND;
	}
	
	public PendingRequest(UUID toUID, UUID groupUID) {
		this.toUID = toUID;
		this.type = ASK_FRIEND;
		this.groupUID = groupUID;
	}
	
	public PendingRequest(Comment comment) {
		this.type = SEND_COMMENT;
		this.comment = comment;
		this.toUID = comment.getPictureUserId();
	}
	
	public PendingRequest(Note note) {
		this.type = SEND_NOTE;
		this.note = note;
		this.toUID = note.getPictureUserId();
	}

	public UUID getToUID() {
		return toUID;
	}
	public void setToUID(UUID toUID) {
		this.toUID = toUID;
	}

	public UUID getGroupUID() {
		return groupUID;
	}

	public int getType() {
		return type;
	}

	public Comment getComment() {
		return comment;
	}

	public Note getNote() {
		return note;
	}

}
