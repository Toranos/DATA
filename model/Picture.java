/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author le-goc
 */
public class Picture implements Serializable {
    private String filename;
    private List<Note> listNotes;
    private List<Tag> listTags;
    private List<Comment> Comments;
    private List<Rule> listRules;

    public Picture(String filename) {
		this.filename = filename;
		this.listNotes = new ArrayList<Note>();
		this.listRules = new ArrayList<Rule>();
		this.listTags = new ArrayList<Tag>();
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
}
