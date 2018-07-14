package sample;

public class Person {
    private String name;
    private String surname;

    public Person(String name, String surname) {
        set(name, surname);
    }

    public void set(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
