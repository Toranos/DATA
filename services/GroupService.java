package DATA.services;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import DATA.exceptions.BadInformationException;
import DATA.model.Group;
import DATA.model.PendingRequest;
import DATA.model.Picture;
import DATA.model.Tag;
import DATA.model.User;
import NET.NetLocalizer;

/**
 * Service for pictures functionalities
 */
public class GroupService {
	
	/**
	 * Public constructor for PictureService
	 */
	public GroupService() {
	}
	
	/**
	 * 
	 * @param user
	 * @param group
	 */
	public void addUserInGroup(User user, Group group) {
		DataService.getInstance().getUser().getListPendingRequests().add(new PendingRequest(user.getUid(), group.getUid()));
	}
	
	/**
	 * 
	 * @param group
	 */
	public void deleteGroup(Group group) {
		if (group.getNom().equals("Autres") == false || group.getNom().equals("Amis")  == false) {
			User currentUser = DataService.getInstance().getUser();
			Iterator<Group> iter = currentUser.getListGroups().iterator();
			while (iter.hasNext()) {
		    	if (iter.next().getUid().equals(group.getUid())) {
		    		iter.remove();
		    		break;
		    	}
		    }
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param group
	 */
	public PendingRequest checkUserPending(User user) {
		User currentUser = DataService.getInstance().getUser();
		for (PendingRequest pendingReq : currentUser.getListPendingRequests()) {
			if(pendingReq.getToUID().equals(user.getUid())){
				return pendingReq;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Group> getGroups() {
		return DataService.getInstance().getUser().getListGroups();
	}

	public void acceptUser(User user, Group group) {
		for (Group groupToAdd : DataService.getInstance().getUser().getListGroups()) {
			if(groupToAdd.getUid().equals(group.getUid())) {
				groupToAdd.getUsers().add(user);
			}
		}
	}

	/**
	 * 
	 * @param user
	 * @param friends
	 */
	public void receiveFriendResponse(User user, boolean friends) {
		User currentUser = DataService.getInstance().getUser();
		for (PendingRequest pendingReq : currentUser.getListPendingRequests()) {
			if(user.getUid().equals(pendingReq.getToUID())){
				if(friends){
					for(Group group : currentUser.getListGroups()){
						if(group.getUid().equals(pendingReq.getGroupUID())){
							group.getUsers().add(user);
						}
					}
				}
				currentUser.getListPendingRequests().remove(pendingReq);
			}
		}
	}
}
