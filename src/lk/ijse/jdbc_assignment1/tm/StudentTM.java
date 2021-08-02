package lk.ijse.jdbc_assignment1.tm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentTM implements Serializable {
    private int id;
    private String name;
    private List<String> contacts = new ArrayList<>();

    public StudentTM() {
    }

    public StudentTM(int id, String name, List<String> contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
