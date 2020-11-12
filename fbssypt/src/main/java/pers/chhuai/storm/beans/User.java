package pers.chhuai.storm.beans;

import java.io.Serializable;

public class User implements Serializable {
	public User() {}

	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", name=" + name + ", gender=" + gender + ", major=" + major
				+ ", type=" + type + "]";
	}

	private String id = null;
	private String pwd = null;
	private String name = null;
	private String gender = null;
	private String major = null;
	private String type = null;
	
	public User(String id, String pwd, String name, String gender, String major,  String type) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.major = major;
		this.type = type;
	}



	public User(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}





