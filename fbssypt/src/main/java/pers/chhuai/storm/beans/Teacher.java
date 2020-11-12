package pers.chhuai.storm.beans;

import java.io.Serializable;

public class Teacher extends User implements Serializable {
	public Teacher(String id, String pwd, String name, String gender, String major, String type) {
		super(id, pwd, name, gender, major, type);
	}
	
	public Teacher(String id, String pwd) {
		super(id, pwd);
	}

	public Teacher() {}

	@Override
	public String toString() {
		return "Teacher{"+super.toString()+"}";
	}
}