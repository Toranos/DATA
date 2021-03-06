package DATA.services;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import DATA.exceptions.BadInformationException;
import DATA.model.Group;
import DATA.model.PendingRequest;
import DATA.model.User;

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
	public boolean addUserInGroup(User user, Group group) {
		List<Group> groupsUserNotIn=getGroupsUserNotIn(user);
		Group groupToAdd = null;
		boolean canAdd = false;
		for (Group userGroup : groupsUserNotIn) {
			if(userGroup.getUid().equals(group.getUid())) {
				canAdd = true;
				groupToAdd = userGroup;
			}
		}
		if(canAdd && groupToAdd.getNom().equals(Group.FRIENDS_GROUP_NAME)) {
			addPendingRequestFriendship(new PendingRequest(user.getUid(), groupToAdd.getUid()));
		} else if(canAdd) {
			acceptUser(user, groupToAdd);
		}
		return canAdd;
	}
	
	public void addPendingRequestFriendship(PendingRequest pendingRequest) {
		List<PendingRequest> pendingRequests = DataService.getInstance().getUser().getListPendingRequests();
		List<PendingRequest> pendingRequestsToRemove = new ArrayList<PendingRequest>();
		for (PendingRequest pendingReq : pendingRequests) {
			if(pendingReq.getToUID().equals(pendingRequest.getToUID()) && (pendingReq.getType() == PendingRequest.ASK_FRIEND || pendingReq.getType() == PendingRequest.ASK_UNFRIEND)) {
				pendingRequestsToRemove.add(pendingReq);
			}
		}
		pendingRequests.removeAll(pendingRequestsToRemove);
		pendingRequests.add(pendingRequest);
	}

	/**
	 * 
	 * @param group
	 */
	public void deleteGroup(Group group) {
		if (group.getNom().equals(Group.FRIENDS_GROUP_NAME) == false || group.getNom().equals(Group.DEFAULT_GROUP_NAME)  == false) {
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
	 * @return
	 */
	public List<Group> getGroups() {
		return DataService.getInstance().getUser().getListGroups();
	}
	
	public void acceptUser(User user, Group group) {
		Group groupUserToAdd = null;
		for (Group groupToAdd : DataService.getInstance().getUser().getListGroups()) {
			if(groupToAdd.getUid().equals(group.getUid())) {
				groupUserToAdd = groupToAdd;
				for(User userGroup : groupToAdd.getUsers()) {
					if(userGroup.getUid().equals(user.getUid())){
						groupUserToAdd = null;
					}
				}
			}
		}
		if(groupUserToAdd != null) {
			groupUserToAdd.getUsers().add(user);
		}
	}

	/**
	 * 
	 * @param user
	 * @param friends
	 */
	public void receiveFriendResponse(User user, boolean friends) {
		List<PendingRequest> pendingRequestsToRemove = new ArrayList<PendingRequest>();
		User currentUser = DataService.getInstance().getUser();
		for (PendingRequest pendingReq : currentUser.getListPendingRequests()) {
			if(pendingReq.getType() == PendingRequest.ASK_FRIEND && user.getUid().equals(pendingReq.getToUID())) {
				pendingRequestsToRemove.add(pendingReq);
				if(friends){
					for(Group group : currentUser.getListGroups()){
						if(group.getUid().equals(pendingReq.getGroupUID()) || group.getNom().equals(Group.FRIENDS_GROUP_NAME)){
							group.getUsers().add(user);
						}
					}
				}
			}
		}
		currentUser.getListPendingRequests().removeAll(pendingRequestsToRemove);
	}
	
	/**
	 * 
	 * @param user
	 * @param friends
	 */
	public void receiveUnfriendResponse(UUID userUid) {
		List<PendingRequest> pendingRequestsToRemove = new ArrayList<PendingRequest>();
		User currentUser = DataService.getInstance().getUser();
		for (PendingRequest pendingReq : currentUser.getListPendingRequests()) {
			if(pendingReq.getType() == PendingRequest.ASK_UNFRIEND && userUid.equals(pendingReq.getToUID())) {
				pendingRequestsToRemove.add(pendingReq);
			}
		}
		currentUser.getListPendingRequests().removeAll(pendingRequestsToRemove);
	}
	
	public void addGroup(Group group) throws BadInformationException{
		if (group == null || group.equals("")) {
			throw new BadInformationException("Group empty");
		}
		if (group.getUid() == null || group.getUid().equals("")) {
			throw new BadInformationException("Uid empty");
		}
		if (group.getUsers() == null || group.getUsers().equals("")) {
			throw new BadInformationException("Users empty");
		}
		if (group.getNom() == null || group.getNom().equals("")) {
			throw new BadInformationException("PictureId empty");
		}
		List<Group> userGroups = DataService.getInstance().getUser().getListGroups();
		boolean groupExists = false;
		for (Group userGroup : userGroups) {
			if (userGroup.getNom().equals(group.getNom())){
				groupExists = true;
			} 
		}
		if(!groupExists) {
			userGroups.add(group);
		} else {
			throw new BadInformationException("Group Name already exists");
		}
	}
	
	public List<User> getUserNotInGroup(Group group){
		List<User> usersNotInGroup= new ArrayList<User>();
		boolean inGroup=false;
		List<Group> userListGroups=DataService.getInstance().getUser().getListGroups();
		for (Group userGroup : userListGroups){
			if (userGroup.getNom().equals(Group.FRIENDS_GROUP_NAME)){
				for (User friend : userGroup.getUsers()){
					for (User userInGroup : group.getUsers()){
						if (friend == userInGroup) {
							inGroup = true;
						}
					}
					if (!inGroup){
						usersNotInGroup.add(friend);
					}
				}
			}
		}
		return usersNotInGroup;

	}
	
	public void deleteUserFromGroup(User user, Group group){
		List<Group> userListGroups=DataService.getInstance().getUser().getListGroups();
		if (group.getNom().equals(Group.FRIENDS_GROUP_NAME)){
			addPendingRequestFriendship((new PendingRequest(user.getUid())));
			for (Group userGroup : userListGroups){
				userGroup.getUsers().remove(user);
			}
		} else {
			group.getUsers().remove(user);
		}
	}
	
	public Group getGroup(String group){
		List<Group> userGroups = DataService.getInstance().getUser().getListGroups();
		for (Group userGroup : userGroups){
			if (userGroup.getNom().equals(group)) {
				return userGroup;
			}
		}
		return null;
	}
	
	public List<Group> getGroupsUserNotIn(User user){
		List<Group> groups = this.getGroups();
		List<Group> groupsUserNotIn= new ArrayList<>();
		boolean userNotIn;
		for (Group group : groups){
			userNotIn = true;
			for (User u : group.getUsers()){
				if (user.getUid().equals(u.getUid())){
					userNotIn=false;
				}
			}
			if (userNotIn){
				groupsUserNotIn.add(group);
			}
		}
		
		return groupsUserNotIn;
		
	}
	
	/**
	 * 
	 * @param user
	 * @param group
	 */
	public List<PendingRequest> getUserPending(User user) {
		List<PendingRequest> pendingReqs = new ArrayList<PendingRequest>();
		User currentUser = DataService.getInstance().getUser();
		for (PendingRequest pendingReq : currentUser.getListPendingRequests()) {
			if(pendingReq.getToUID().equals(user.getUid())){
				pendingReqs.add(pendingReq);
			}
		}
		return pendingReqs;
	}
}
