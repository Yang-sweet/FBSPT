package pers.chhuai.storm.beans;

import java.io.Serializable;

public class Admin extends User implements Serializable {

	public Admin(String id, String pwd, String name, String gender, String major,  String type) {
		super(id, pwd, name, gender, major, type);
	}
	
	public Admin(String id, String pwd) {
		super(id, pwd);
	}

	public Admin() {}
}