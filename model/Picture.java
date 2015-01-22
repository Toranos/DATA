/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author le-goc
 */
public class Picture implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;

    private String filename;
    private String description;
    private String title;
    private List<Note> listNotes;
    private List<Tag> listTags;
    private List<Comment> listComments;
    private List<Rule> listRules;
    /**
     * LightUser with UUID, login, firstname, ...
     */
    private User user;
    
    /**
     * Id of the picture
     */
    private UUID uid; 
    
    /**
     * The byte array containing the image in HD
     * Is null by default unless the HD definition is needed
     */
    private byte[] pixels;
    
    private byte[] icon;

    public Picture(String filename, String description, String title, User user) {
		this.filename = filename;
		this.description = description;
		this.title = title;
		this.listNotes = new ArrayList<Note>();
		this.listRules = new ArrayList<Rule>();
		this.listTags = new ArrayList<Tag>();
		this.listComments = new ArrayList<Comment>();
		this.user = user;
		this.uid = UUID.randomUUID();
	}
    
    public Picture(String filename, String description, User user) {
		this.filename = filename;
		this.description = description;
		this.listNotes = new ArrayList<Note>();
		this.listRules = new ArrayList<Rule>();
		this.listTags = new ArrayList<Tag>();
		this.listComments = new ArrayList<Comment>();
		this.user = user;
		this.uid = UUID.randomUUID();
	}
    
    public Picture(String filename, User user) {
		this.filename = filename;
		this.listNotes = new ArrayList<Note>();
		this.listRules = new ArrayList<Rule>();
		this.listTags = new ArrayList<Tag>();
		this.user = user;
		this.uid = UUID.randomUUID();
	}
    
    /**
     * Create a copy of the picture (ony the rule list is different)
     * @param p The picture to copy
     */
    public Picture(Picture p){
    	this.filename = p.getFilename();
    	this.description = p.getDescription();
    	this.title = p.getTitle();
    	this.listNotes = p.getListNotes();
    	this.listTags = p.getListTags();
    	this.listComments = p.getComments();
    	this.listRules = new ArrayList<Rule>();
    	for(Rule rule : p.getListRules()){
    		this.listRules.add(rule);
    	}
    }
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getNoteAverage() {
    	int numberOfNote = 0, sumNote = 0;
    	Iterator<Note> iter = this.getListNotes().iterator();
	    while (iter.hasNext()) {
	    	numberOfNote++;
	    	sumNote+=iter.next().getValue();
	    }
    	if (numberOfNote == 0) {
    		return 0;
    	} else {
    		return sumNote/(float)numberOfNote;
    	}
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UUID getUid() {
		return uid;
	}
    
	public List<Rule> getListRules() {
		return listRules;
	}

	public void setListRules(List<Rule> listRules) {
		this.listRules = listRules;
	}

	public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Note> getListNotes() {
        return listNotes;
    }

    public void setListNotes(List<Note> listNotes) {
        this.listNotes = listNotes;
    }

    public List<Tag> getListTags() {
        return listTags;
    }

    public void setListTags(List<Tag> listTags) {
        this.listTags = listTags;
    }

    public List<Comment> getComments() {
        return listComments;
    }

    public void getComments(List<Comment> listComments) {
        this.listComments = listComments;
    }

	public byte[] getPixels() {
		return pixels;
	}

	public void setPixels(byte[] pixels) {
		this.pixels = pixels;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

    public Image getImageObject() {
        ByteArrayInputStream in = new ByteArrayInputStream(pixels);
        try
        {
            BufferedImage read = ImageIO.read(in);
            return SwingFXUtils.toFXImage(read, null);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    // returns null if user has not yet voted
    public Note getNoteFromUser(Picture p, User u) {
        for (Note n : p.getListNotes()) {
            if (n.getNoteUser().getUid().equals(u.getUid())) {
                return n;
            }
        }
        return null;
    }

	public boolean hasAccess(User sendMan, User currentUser) {
		for(Rule rule : listRules){
			if(rule.getGroup().getNom().equals(Group.DEFAULT_GROUP_NAME) && rule.isCanView()){
				return true;
			}
			for(Group g : currentUser.getListGroups()){
				if(g.getNom().equals(rule.getGroup().getNom())){
					if(rule.isCanView() && g.contain(sendMan)){
						return true;
					}
				}
			}
		}
		return false;
	}


//    private static byte[] ImageToBase64(ImageIcon i) {
//        try
//        {
//            BufferedImage bi = (BufferedImage) i.getImage();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(bi, "png", baos);
//            byte[] dataToEncode = baos.toByteArray();
//            byte[] base64Data = Base64.encode(dataToEncode);
//            return base64Data;
//        } catch (IOException ioe)
//        {
//            return null;
//        }
//    }
}
