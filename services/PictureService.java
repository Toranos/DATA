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
		picture.setPixels(imageToByte(picture.getFilename()));
//		picture.setIcon(new ImageIcon(picture.getFilename()));
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
