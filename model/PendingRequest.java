package DATA.model;

import java.io.Serializable;
import java.util.UUID;

public class PendingRequest implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	
	private UUID toUID;
	private UUID groupUID;
	
	public PendingRequest(UUID toUID, UUID groupUID) {
		this.toUID = toUID;
		this.setGroupUID(groupUID);
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
