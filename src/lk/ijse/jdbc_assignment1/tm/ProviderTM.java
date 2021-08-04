package lk.ijse.jdbc_assignment1.tm;

public class ProviderTM {
    private int id;
    private String name;

    public ProviderTM(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProviderTM() {
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

    @Override
    public String toString() {
        return "ProviderTM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
