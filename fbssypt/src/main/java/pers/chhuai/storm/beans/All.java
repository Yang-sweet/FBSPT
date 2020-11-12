package pers.chhuai.storm.beans;

import java.io.Serializable;

public class All extends User implements Serializable {

    public All(String id, String pwd, String name, String gender, String major,  String type) {
        super(id, pwd, name, gender, major, type);
    }

    public All(String id, String pwd) {
        super(id, pwd);
    }

    public All() {}
}
