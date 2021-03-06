package DATA.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import DATA.exceptions.BadInformationException;
import DATA.exceptions.PictureAlreadyExisted;
import DATA.model.Comment;
import DATA.model.Group;
import DATA.model.Note;
import DATA.model.PendingRequest;
import DATA.model.Picture;
import DATA.model.Rule;
import DATA.model.Tag;
import DATA.model.User;

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
	public List<Picture> getPictures(List<Tag> listtag, User sendMan) {
		List<Picture> resultPictures;
		if(listtag != null && !listtag.isEmpty()) {
			resultPictures = new ArrayList<Picture>();
			//faire une autre fonction avec un return pour casser les 3 for
			for (Picture picture : DataService.getInstance().getUser().getListPictures()) {
				if(sendMan.getUid().equals(picture.getUser().getUid()) && picture.hasAccess(sendMan, DataService.getInstance().getUser())){
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
			}
		} else if(sendMan.getUid().equals(DataService.getInstance().getUser().getUid())) {
			resultPictures = new ArrayList<Picture>(DataService.getInstance().getUser().getListPictures());
		} else {
			resultPictures = new ArrayList<Picture>();
			for (Picture picture : DataService.getInstance().getUser().getListPictures()) {
				if(picture.hasAccess(sendMan, DataService.getInstance().getUser())){
					resultPictures.add(picture);
				}
			}
		}
		return resultPictures;
	}
	
	/**
	 * Get remote user's pictures
	 * @param listUser
	 * @return
	 */
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
	 * Add an avatar to the profile
	 * @param filename
	 */
	public void addAvatar(String filename) {
		DataService.getInstance().getUser().setAvatar(imageToByte(filename));
	}
	
	/**
	 * 
	 * @param picture
	 * @throws IOException 
	 * @throws PictureAlreadyExisted 
	 */
	public void addPicture(Picture picture) throws IOException, PictureAlreadyExisted {
		picture.setPixels(imageToByte(picture.getFilename()));
		if (this.copyImageToWorkspace(picture)) {
			picture.getListRules().add(new Rule(true, true, true, picture, DataService.getInstance().getUser().getOtherGroup()));
			for(Group g : DataService.getInstance().getUser().getListGroups()){
				if(g.getNom().equals(Group.FRIENDS_GROUP_NAME)){
					picture.getListRules().add(new Rule(true, true, true, picture, g));
					break;
				}
			}
			for(Group g : DataService.getInstance().getUser().getListGroups()){
				if(!g.getNom().equals(Group.FRIENDS_GROUP_NAME)){
					picture.getListRules().add(new Rule(true, true, true, picture, g));
				}
			}
			DataService.getInstance().getUser().getListPictures().add(picture);
		}
	}
	
	/**
	 * Copie image to workspace location.
	 * @param img
	 * @return
	 * @throws IOException
	 * @throws PictureAlreadyExisted 
	 */
	public boolean copyImageToWorkspace(Picture img) throws IOException, PictureAlreadyExisted {
		File f = new File(img.getFilename());	
		img.setTitle(f.getName());
		FileInputStream sourceFile = new FileInputStream(f);
		File imgDir = DataService.getInstance().getImagePathUser();
		if (imgDir.exists() == false) {
			imgDir.mkdir();
		}
		String newFilename = imgDir.getPath();
		newFilename += File.separator+img.getUid().toString();
		
		// Extension of file.
		String ext = ".png";
		String name = f.getName();
		if (name.lastIndexOf(".") > 0) {
		    ext = name.substring(name.lastIndexOf("."));
		    if (!".png".equals(ext) && !".png".equals(ext) && !".jpeg".equals(ext) && !".gif".equals(ext) && !".bmp".equals(ext)) {
		    	ext = ".png";
		    }
		} 
		newFilename += ext;
		
		// Copie du fichier.
		File newFile = new File(newFilename);
		if (newFile.exists()) {
			sourceFile.close();
			throw new PictureAlreadyExisted("Picture exits");
		}
		img.setFilename(newFilename);
		FileOutputStream destinationFile = new FileOutputStream(newFile);
		byte buffer[] = new byte[512 * 1024];
		int nbLecture;
		while ((nbLecture = sourceFile.read(buffer)) != -1){
			destinationFile.write(buffer, 0, nbLecture);
		}
		destinationFile.close();
		sourceFile.close();
		return true;
	}
	
	/**
	 * 
	 * @param comment
	 */
	public boolean addComment(Comment comment) throws BadInformationException {
		if (comment == null) {
			throw new BadInformationException("Comment empty");
		}
		if (comment.getUid() == null) {
			throw new BadInformationException("Uid empty");
		}
		if (comment.getCommentUser().getUid() == null) {
			throw new BadInformationException("CommentUserId empty");
		}
		if (comment.getPictureId() == null) {
			throw new BadInformationException("PictureId empty");
		}
		if (comment.getPictureUserId() == null) {
			throw new BadInformationException("PictureUserId empty");
		}
		
		User currentUser = DataService.getInstance().getUser();
		if (currentUser.getUid().equals(comment.getPictureUserId())) {
			Iterator<Picture> iterPicture = currentUser.getListPictures().iterator();
			boolean isUpdate;
			Picture tmpPicture = null;
			Comment tmpComment = null;
		    while (iterPicture.hasNext()) {
		    	tmpPicture = iterPicture.next();
		    	if (tmpPicture.getUid().equals(comment.getPictureId())) {
		    		//Search if the Comment already exist to update it
		    		isUpdate = false;
		    		Iterator<Comment> iterComment = tmpPicture.getComments().iterator();
		    		while (iterComment.hasNext()) {
		    			tmpComment = iterComment.next();
		    			if (tmpComment.getUid().equals(comment.getUid())) {
		    				if (tmpComment.getCommentUser().getUid().equals(comment.getCommentUser().getUid())) {
		    					tmpComment.setValue(comment.getValue());
			    				isUpdate = true;
		    				}
		    				break;
		    			}
		    		}
		    		//If it doesn't exist we add it
		    		if (isUpdate == false) {
		    			tmpPicture.getComments().add(comment);
			    		break;	
		    		}
		    	}
		    }
		    return true;
		}  else {
			DataService.getInstance().getUser().getListPendingRequests().add(new PendingRequest(comment));
			return false;
		}
	}
	
	/**
	 * 
	 * @param note
	 */
	public boolean addNote(Note note) throws BadInformationException {
		if (note == null) {
			throw new BadInformationException("Note empty");
		}
		if (note.getUid() == null) {
			throw new BadInformationException("Uid empty");
		}
		if (note.getNoteUser() == null) {
			throw new BadInformationException("NoteUserId empty");
		}
		if (note.getPictureId() == null) {
			throw new BadInformationException("PictureId empty");
		}
		if (note.getPictureUserId() == null) {
			throw new BadInformationException("PictureUserId empty");
		}
		
		User currentUser = DataService.getInstance().getUser();
		if (currentUser.getUid().equals(note.getPictureUserId())) {
			Iterator<Picture> iterPicture = currentUser.getListPictures().iterator();
			boolean isUpdate;
			Picture tmpPicture = null;
			Note tmpNote = null;
		    while (iterPicture.hasNext()) {
		    	tmpPicture = iterPicture.next();
		    	if (tmpPicture.getUid().equals(note.getPictureId())) {
		    		//Search if the Note already exist to update it
		    		isUpdate = false;
		    		Iterator<Note> iterNote = tmpPicture.getListNotes().iterator();
		    		while (iterNote.hasNext()) {
		    			tmpNote = iterNote.next();
		    			if (tmpNote.getUid().equals(note.getUid())) {
		    				if (tmpNote.getNoteUser().getUid().equals(note.getNoteUser().getUid())) {
		    					tmpNote.setValue(note.getValue());
		    					isUpdate = true;
		    				}
		    				break;
		    			}
		    		}
		    		//If it doesn't exist we add it
		    		if (isUpdate == false) {
		    			tmpPicture.getListNotes().add(note);
		    			break;	
		    		}
		    	}
		    }
		    return true;
		}  else {
			DataService.getInstance().getUser().getListPendingRequests().add(new PendingRequest(note));
			return false;
		}
	}
	
	/**
	 * 
	 * @param comment
	 */
	public boolean deleteComment(Comment comment) throws BadInformationException {
		if (comment == null) {
			throw new BadInformationException("Comment empty");
		}
		if (comment.getUid() == null) {
			throw new BadInformationException("Uid empty");
		}
		if (comment.getCommentUser().getUid() == null) {
			throw new BadInformationException("CommentUserId empty");
		}
		if (comment.getPictureId() == null) {
			throw new BadInformationException("PictureId empty");
		}
		if (comment.getPictureUserId() == null) {
			throw new BadInformationException("PictureUserId empty");
		}
		
		User currentUser = DataService.getInstance().getUser();
		Picture tmpPicture = null;
		Comment tmpComment = null;
	    	
		if (currentUser.getUid().equals(comment.getPictureUserId())) {
			Iterator<Picture> iterPicture = currentUser.getListPictures().iterator();
		    while (iterPicture.hasNext()) {
		    	tmpPicture = iterPicture.next();
		    	if (tmpPicture.getUid().equals(comment.getPictureId())) {
		    		Iterator<Comment> iterComment = tmpPicture.getComments().iterator();
		    		while (iterComment.hasNext()) {
		    			tmpComment = iterComment.next();
		    			if (tmpComment.getUid().equals(comment.getUid())) {
		    				iterComment.remove();
		    				break;
		    			}
		    		}
		    		break;
		    	}
		    }
		    return true;
		} else {
			DataService.getInstance().getUser().getListPendingRequests().add(new PendingRequest(comment));
			return false;
		}
	}

	public boolean deleteNote(Note note) throws BadInformationException {
		if (note == null || "".equals(note)) {
			throw new BadInformationException("note empty");
		}
		if (note.getUid() == null || "".equals(note.getUid())) {
			throw new BadInformationException("Uid empty");
		}
		if (note.getNoteUser().getUid() == null || "".equals(note.getNoteUser().getUid())) {
			throw new BadInformationException("CommentUserId empty");
		}
		if (note.getPictureId() == null || "".equals(note.getPictureId())) {
			throw new BadInformationException("PictureId empty");
		}
		if (note.getPictureUserId() == null || "".equals(note.getPictureUserId())) {
			throw new BadInformationException("PictureUserId empty");
		}
		
		User currentUser = DataService.getInstance().getUser();
		if (currentUser.getUid().equals(note.getPictureUserId()) || currentUser.getUid().equals(note.getNoteUser().getUid())) {
			Iterator<Picture> iterPicture = currentUser.getListPictures().iterator();
		    while (iterPicture.hasNext()) {
		    	if (iterPicture.next().getUid().equals(note.getPictureId())) {
		    		Iterator<Note> iterNote = iterPicture.next().getListNotes().iterator();
		    		while (iterNote.hasNext()) {
		    			if (iterNote.next().getUid().equals(note.getUid())) {
		    				iterNote.remove();
		    				break;
		    			}
		    		}
		    		break;
		    	}
		    }
		    return true;
		}  else {
			return false;
		}
	}
	
	/**
	 * Create byte array from image.
	 * @param filename
	 * @return
	 */
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
				Logger.getLogger(PictureService.class.getName()).log(Level.SEVERE, "Error in changing image to byte.");  
			}
		} catch (Exception e) {  
			Logger.getLogger(PictureService.class.getName()).log(Level.SEVERE, "Exception during serialization: " + e);  
		}
		return packet;
	}

	/**
	 * Delete image.
	 * @param picture
	 */
	public void deletePicture(Picture picture) {
		File f = new File(picture.getFilename());	
		if (f.exists()) {
			f.delete();
		}
		DataService.getInstance().getUser().getListPictures().remove(picture);
		picture = null;
	}

	public void receiveCommentResponse(UUID commentUid) {
		List<PendingRequest> pendingRequests = DataService.getInstance().getUser().getListPendingRequests();
		PendingRequest pendingRequestToRemove = null;
		for (PendingRequest pendingRequest : pendingRequests) {
			if(pendingRequest.getType() == PendingRequest.SEND_COMMENT && pendingRequest.getComment().getUid().equals(commentUid)) {
				pendingRequestToRemove = pendingRequest;
			}
		}
		pendingRequests.remove(pendingRequestToRemove);
	}

	public void receiveNoteResponse(UUID noteUid) {
		List<PendingRequest> pendingRequests = DataService.getInstance().getUser().getListPendingRequests();
		PendingRequest pendingRequestToRemove = null;
		for (PendingRequest pendingRequest : pendingRequests) {
			if(pendingRequest.getType() == PendingRequest.SEND_NOTE && pendingRequest.getNote().getUid().equals(noteUid)) {
				pendingRequestToRemove = pendingRequest;
			}
		}
		pendingRequests.remove(pendingRequestToRemove);
	}
	
	/**
	 * Create a rule concatenating the three rules 
	 * @param id		Id of the picture
	 * @param sendMan	User requesting the picture
	 * @return		a rule
	 */
	public Rule getMaxRule(UUID id, User sendMan) {
		Picture picture = getPictureById(id);
		Rule rule = new Rule(false, false, false, picture, new Group(sendMan.getUid().toString()));
		for(Rule r : picture.getListRules()){
			if(r.getGroup().getNom().equals(Group.DEFAULT_GROUP_NAME)){
				if(r.isCanComment()){
					rule.setCanComment(true);
				}
				if(r.isCanRate()){
					rule.setCanRate(true);
				}
				if(r.isCanView()){
					rule.setCanView(true);
				}
			}
			for(Group g : DataService.getInstance().getUser().getListGroups()){
				if(g.getNom().equals(r.getGroup().getNom()) && g.contain(sendMan)){
					if(r.isCanComment()){
						rule.setCanComment(true);
					}
					if(r.isCanRate()){
						rule.setCanRate(true);
					}
					if(r.isCanView()){
						rule.setCanView(true);
					}
					break;
				}
			}
		}
		return rule;
	}

	public void addGroupsRule(Group group) {
		for(Picture p : DataService.getInstance().getUser().getListPictures()){
			p.getListRules().add(new Rule(true, true, true, p, group));
		}
		
	}

	public void deleteGroupsRule(Group group) {
		for(Picture p : DataService.getInstance().getUser().getListPictures()){
			List<Rule> deletedRules = new ArrayList<Rule>();
			for(Rule r : p.getListRules()){
				if(r.getGroup().getNom().equals(group.getNom())){
					deletedRules.add(r);
				}
			}
			p.getListRules().removeAll(deletedRules);
		}
	}	
}
