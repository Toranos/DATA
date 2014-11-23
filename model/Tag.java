/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.io.Serializable;

/**
 *
 * @author le-goc
 */
public class Tag implements Serializable {
	
	/**
	* Serialization UID.
	*/
	public static final long serialVersionUID = 1L;
	
    private String value;

    public Tag(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
