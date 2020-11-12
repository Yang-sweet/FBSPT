package pers.chhuai.storm.beans;
public class Student extends User {
    public Student() {}

    @Override
    public String toString() {
        return "Student{" + super.toString() +
                '}';
    }

    public Student(String id, String pwd, String name, String gender, String major, String type) {
        super(id, pwd, name, gender, major, type);
    }

    public Student(String id, String pwd) {
        super(id, pwd);
    }
}