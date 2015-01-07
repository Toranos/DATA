package DATA.services;

import java.util.Iterator;
import java.util.ArrayList;
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
		for (Group userGroup : userGroups) {
			if (userGroup.getNom() == group.getNom()){
				throw new BadInformationException("Group Name already exists");
			} else {
				userGroups.add(group);
			}
		}
	}
	
	public List<User> getUserNotInGroup(Group group){
		List<User> usersNotInGroup= new ArrayList<User>();
		boolean inGroup=false;
		List<Group> userListGroups=DataService.getInstance().getUser().getListGroups();
		for (Group userGroup : userListGroups){
			if (userGroup.getNom() == Group.FRIENDS_GROUP_NAME){
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
		if (group.getNom() == Group.FRIENDS_GROUP_NAME){
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
			if (userGroup.getNom() == group) {
				return userGroup;
			}
		}
		return null;
	}
	
	public List<Group> getGroupsUserNotIn(User user){
		List<Group> groups = this.getGroups();
		List<Group> groupsUserNotIn= new ArrayList<>();
		boolean userNotIn=true;
		for (Group group : groups){
			for (User u : group.getUsers()){
				if (user==u){
					userNotIn=false;
				}
			}
			if (userNotIn){
				groupsUserNotIn.add(group);
			}
		}
		
		return groupsUserNotIn;
		
	}
}
