package DATA.model;

import java.io.Serializable;
import java.util.UUID;

public class PendingRequest implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	
	private UUID fromUID;
	private UUID toUID;
	private UUID groupUID;
	
	public PendingRequest(UUID fromUID, UUID toUID, UUID groupUID) {
		this.fromUID = fromUID;
		this.toUID = toUID;
		this.setGroupUID(groupUID);
	}
	
	public UUID getFromUID() {
		return fromUID;
	}
	public void setFromUID(UUID fromUID) {
		this.fromUID = fromUID;
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

	public void setGroupUID(UUID groupUID) {
		this.groupUID = groupUID;
	}
	

}
