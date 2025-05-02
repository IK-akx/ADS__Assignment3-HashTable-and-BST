package models;

public class MyTestingClass {
    private int id;

    public MyTestingClass(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) o;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }
}
