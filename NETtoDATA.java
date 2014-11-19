package data.interfaces;

public interface NETtoDATA 
{
	
	public void addComment(Comment comment) ;
	

	public List<IP> getConnectedIps() ;
	

	public User getProfil() ;
	

	public void infoUser(User user, int idRequest) ;
	

	public void receiveFriendRequest(long idSender) ;
	
	
	public void resultPictures(List<Picture> pictures, int idRequest) ;


	public void sendPictureToPage(Picture picture, int pageId) ;


	public void setConnectedUser(User user) ;
	
	
}

