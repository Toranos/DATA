/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.util.Date;

/**
 *
 * @author le-goc
 */
public class Comment {
    private String value;
    private Date dateTime;

    public Comment(String value, Date dateTime) {
		this.value = value;
		this.dateTime = dateTime;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
