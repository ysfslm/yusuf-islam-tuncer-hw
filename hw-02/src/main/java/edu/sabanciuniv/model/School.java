package edu.sabanciuniv.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private int numberOfStudents;
    private String address;

    @OneToMany(mappedBy = "school")
    private List<Student> studentList = new ArrayList<>();

    public School(String name, int numberOfStudents, String address) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        this.address = address;
    }

    public School() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (!Objects.equals(name, school.name)) return false;
        return Objects.equals(address, school.address);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                ", address='" + address + '\'' +
                '}';
    }
}

