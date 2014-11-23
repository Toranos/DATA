package DATA.model;

import java.io.Serializable;

public class Rule implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	
	private boolean canView;
	private boolean canRate;
	private boolean canComment;
	private Picture picture;
	private Group group;
	
	public Rule(boolean canView, boolean canRate, boolean canComment,
			Picture picture, Group group) {
		this.canView = canView;
		this.canRate = canRate;
		this.canComment = canComment;
		this.picture = picture;
		this.group = group;
	}

	public boolean isCanView() {
		return canView;
	}

	public void setCanView(boolean canView) {
		this.canView = canView;
	}

	public boolean isCanRate() {
		return canRate;
	}

	public void setCanRate(boolean canRate) {
		this.canRate = canRate;
	}

	public boolean isCanComment() {
		return canComment;
	}

	public void setCanComment(boolean canComment) {
		this.canComment = canComment;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
