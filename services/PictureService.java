package DATA.services;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.swing.ImageIcon;

import DATA.exceptions.BadInformationException;
import DATA.model.Comment;
import DATA.model.Note;
import DATA.model.Picture;
import DATA.model.Tag;
import DATA.model.User;
import NET.NetLocalizer;

/**
 * Service for pictures functionalities
 */
public class PictureService {

	/**
	 * Public constructor for PictureService
	 */
	public PictureService() {
	}
	
	/**
	 * Retrieve local picture by its ID
	 * @param UUID of the Picture
	 * @return	The picture identified
	 * @throws BadInformationException	When incorrect information found
	 */
	public Picture getPictureById(UUID pictureUid) throws BadInformationException {
		List<Picture> pictures = DataService.getInstance().getUser().getListPictures();
		for (Picture pic : pictures) {
			if (pic.getUid().equals(pictureUid)) {
				return pic;
			}
		}
		return null;
	}
	
	/**
	 * Retrieve local pictures by their tags, or all pictures if tags null
	 * @param List Tag for the picture
	 * @return	List Picture identified
	 * @throws BadInformationException	When incorrect information found
	 */
	public List<Picture> getPictures(List<Tag> listtag) throws BadInformationException {
		return null;
	}
	
	/**
	 * 
	 * @param picture
	 */
	public void addPicture(Picture picture) {
		picture.setImageIcon(new ImageIcon(picture.getFilename(), "Une icone d'image"));
		DataService.getInstance().getUser().getListPictures().add(picture);
	}
	
	/**
	 * 
	 * @param comment
	 */
	public void addComment(Comment comment) {
		User currentUser = DataService.getInstance().getUser();
		Iterator<Picture> iter = currentUser.getListPictures().iterator();
	    while (iter.hasNext()) {
	    	if (iter.next().getUid() == comment.getPictureId()) {
	    		iter.next().getComments().add(comment);
	    	}
	    }
	}
	
	/**
	 * 
	 * @param note
	 */
	public void addNote(Note note) {
		User currentUser = DataService.getInstance().getUser();
		Iterator<Picture> iter = currentUser.getListPictures().iterator();
	    while (iter.hasNext()) {
	    	if (iter.next().getUid() == note.getPictureId()) {
	    		iter.next().getListNotes().add(note);
	    	}
	    }
	}
	
}
