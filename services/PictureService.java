package DATA.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
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
	
	public List<Picture> getPicturesByUser(List<String> listUser) {
		List<Picture> resultPictures = new ArrayList<Picture>();
		if(listUser != null && !listUser.isEmpty()) { 
			for(String user : listUser){
				if(user.equals(DataService.getInstance().getUser().getLogin())){
					return DataService.getInstance().getUser().getListPictures();
				}
			}
		}
		return resultPictures;
	}
	
	/**
	 * 
	 * @param picture
	 */
	public void addPicture(Picture picture) {

		picture.setPixels(imageToByte(picture.getFilename()));
//		picture.setIcon(new ImageIcon(picture.getFilename()));
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
	public boolean addComment(Comment comment) throws BadInformationException {
		if (comment == null || comment.equals("")) {
			throw new BadInformationException("Comment empty");
		}
		if (comment.getUid() == null || comment.getUid().equals("")) {
			throw new BadInformationException("Uid empty");
		}
		if (comment.getCommentUser().getUid() == null || comment.getCommentUser().getUid().equals("")) {
			throw new BadInformationException("CommentUserId empty");
		}
		if (comment.getPictureId() == null || comment.getPictureId().equals("")) {
			throw new BadInformationException("PictureId empty");
		}
		if (comment.getPictureUserId() == null || comment.getPictureUserId().equals("")) {
			throw new BadInformationException("PictureUserId empty");
		}
		
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
	public boolean addNote(Note note) throws BadInformationException {
		if (note == null || note.equals("")) {
			throw new BadInformationException("Note empty");
		}
		if (note.getUid() == null || note.getUid().equals("")) {
			throw new BadInformationException("Uid empty");
		}
		if (note.getNoteUser() == null || note.getNoteUser().equals("")) {
			throw new BadInformationException("NoteUserId empty");
		}
		if (note.getPictureId() == null || note.getPictureId().equals("")) {
			throw new BadInformationException("PictureId empty");
		}
		if (note.getPictureUserId() == null || note.getPictureUserId().equals("")) {
			throw new BadInformationException("PictureUserId empty");
		}
		
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

	public byte[] imageToByte(String filename){
		byte[] packet = new byte[0];
		try {
			BufferedImage img = null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				img = ImageIO.read(new File(filename));
				ImageIO.write(
						img,
						filename.substring(
								filename.lastIndexOf(".") + 1),
						baos);
				packet =  baos.toByteArray();
			} catch (IOException ex) {
				ex.printStackTrace();  
			}
		} catch (Exception e) {  
			System.out.println("Exception during serialization: " + e);  
			System.exit(0);  
		}
		return packet;
	}	
}
