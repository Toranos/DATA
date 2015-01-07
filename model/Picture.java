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
    private List<Note> listNotes;
    private List<Tag> listTags;
    private List<Comment> Comments;
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
    private byte[][] pixels;
    
    private ImageIcon imageIcon;

    public Picture(String filename, String description, User user) {
		this.filename = filename;
		this.description = description;
		this.listNotes = new ArrayList<Note>();
		this.listRules = new ArrayList<Rule>();
		this.listTags = new ArrayList<Tag>();
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
        return Comments;
    }

    public void setComments(List<Comment> Comments) {
        this.Comments = Comments;
    }

	public byte[][] getPixels() {
		return pixels;
	}

	public void setPixels(byte[][] pixels) {
		this.pixels = pixels;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

    public Image getIconAsImageObject() {
        byte[] iconAsArray = ImageToBase64(this.getImageIcon());
        ByteArrayInputStream in = new ByteArrayInputStream(iconAsArray);
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


    private static byte[] ImageToBase64(ImageIcon i) {
        try
        {
            BufferedImage bi = (BufferedImage) i.getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] dataToEncode = baos.toByteArray();
            byte[] base64Data = Base64.encode(dataToEncode);
            return base64Data;
        } catch (IOException ioe)
        {
            return null;
        }
    }
}
