package DATA.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.swing.ImageIcon;

import javafx.scene.image.Image;
import DATA.exceptions.BadInformationException;
import DATA.model.Comment;
import DATA.model.Group;
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
	 */
	public Picture getPictureById(UUID pictureUid) {
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
	 */
	public List<Picture> getPictures(List<Tag> listtag) {
		List<Picture> resultPictures;
		if(listtag != null && !listtag.isEmpty()) {
			resultPictures = new ArrayList<Picture>();
			//faire une autre fonction avec un return pour casser les 3 for
			for (Picture picture : DataService.getInstance().getUser().getListPictures()) {
				SEARCHLOOP: for (Tag tag : picture.getListTags()) {
					for(Tag customTag : listtag) {
						if(tag.getValue() != null && customTag.getValue() != null
							&& tag.getValue().equals(customTag.getValue())) {
							resultPictures.add(picture);
							break SEARCHLOOP;
						}
					}
				}
			}
		} else {
			resultPictures = new ArrayList<Picture>(DataService.getInstance().getUser().getListPictures());
		}
		return resultPictures;
	}
	
	/**
	 * 
	 * @param picture
	 */
	public void addPicture(Picture picture) {
		picture.setImageIcon(new ImageIcon(picture.getFilename()));
		DataService.getInstance().getUser().getListPictures().add(picture);
	}
	
	/**
	 * 
	 * @param picture
	 */
	public void deletePicture(Picture picture) {
		User currentUser = DataService.getInstance().getUser();
		Iterator<Picture> iter = currentUser.getListPictures().iterator();
		while (iter.hasNext()) {
	    	if (iter.next().getUid().equals(picture.getUid())) {
	    		iter.remove();
	    		break;
	    	}
	    }
	}
	
	/**
	 * 
	 * @param comment
	 */
	public boolean addComment(Comment comment) {
		User currentUser = DataService.getInstance().getUser();
		if (currentUser.getUid().equals(comment.getPictureUserId())) {
			Iterator<Picture> iterPicture = currentUser.getListPictures().iterator();
			boolean isUpdate;
		    while (iterPicture.hasNext()) {
		    	if (iterPicture.next().getUid().equals(comment.getPictureId())) {
		    		//Search if the Comment already exist to update it
		    		isUpdate = false;
		    		Iterator<Comment> iterComment = iterPicture.next().getComments().iterator();
		    		while (iterComment.hasNext()) {
		    			if (iterComment.next().getUid().equals(comment.getUid())) {
		    				if (iterComment.next().getCommentUser().getUid().equals(comment.getCommentUser().getUid())) {
		    					iterComment.next().setValue(comment.getValue());
			    				isUpdate = true;
		    				}
		    				break;
		    			}
		    		}
		    		//If it doesn't exist we add it
		    		if (isUpdate == false) {
		    			iterPicture.next().getComments().add(comment);
			    		break;	
		    		}
		    	}
		    }
		    return true;
		}  else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param note
	 */
	public boolean addNote(Note note) {
		User currentUser = DataService.getInstance().getUser();
		if (currentUser.getUid().equals(note.getPictureUserId())) {
			Iterator<Picture> iterPicture = currentUser.getListPictures().iterator();
			boolean isUpdate;
		    while (iterPicture.hasNext()) {
		    	if (iterPicture.next().getUid().equals(note.getPictureId())) {
		    		//Search if the Note already exist to update it
		    		isUpdate = false;
		    		Iterator<Note> iterNote = iterPicture.next().getListNotes().iterator();
		    		while (iterNote.hasNext()) {
		    			if (iterNote.next().getUid().equals(note.getUid())) {
		    				if (iterNote.next().getNoteUser().getUid().equals(note.getNoteUser().getUid())) {
		    					iterNote.next().setValue(note.getValue());
		    					isUpdate = true;
		    				}
		    				break;
		    			}
		    		}
		    		//If it doesn't exist we add it
		    		if (isUpdate == false) {
		    			iterPicture.next().getListNotes().add(note);
		    			break;	
		    		}
		    	}
		    }
		    return true;
		}  else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param comment
	 */
	public void deleteComment(Comment comment) {
		User currentUser = DataService.getInstance().getUser();
		if (currentUser.getUid().equals(comment.getPictureUserId()) || currentUser.getUid().equals(comment.getCommentUser().getUid())) {
			Iterator<Picture> iterPicture = currentUser.getListPictures().iterator();
		    while (iterPicture.hasNext()) {
		    	if (iterPicture.next().getUid().equals(comment.getPictureId())) {
		    		Iterator<Comment> iterComment = iterPicture.next().getComments().iterator();
		    		while (iterComment.hasNext()) {
		    			if (iterComment.next().getUid().equals(comment.getUid())) {
		    				iterComment.remove();
		    				break;
		    			}
		    		}
		    		break;
		    	}
		    }
		}
	}
}
