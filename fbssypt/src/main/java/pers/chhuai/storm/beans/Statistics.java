package pers.chhuai.storm.beans;

public class Statistics {
    private String id;
    private String name;
    private int finished;

    public Statistics(){}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public Statistics(String id, String name, int finished) {
        this.id = id;
        this.name = name;
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", finished=" + finished +
                '}';
    }
}
