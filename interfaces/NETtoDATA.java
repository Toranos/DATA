package DATA.interfaces;

import java.net.Inet4Address;
import java.util.List;

import DATA.model.Comment;
import DATA.model.Picture;
import DATA.model.User;

public interface NETtoDATA {
	public void addComment(Comment comment) ;
	

	public List<Inet4Address> getConnectedIps() ;
	
	public User getProfil() ;
	
	public void infoUser(User user, int idRequest) ;
	

	public void receiveFriendRequest(long idSender) ;
	
	
	public void resultPictures(List<Picture> pictures, int idRequest) ;


	public void sendPictureToPage(Picture picture, int pageId) ;


	public void setConnectedUser(User user) ;
}