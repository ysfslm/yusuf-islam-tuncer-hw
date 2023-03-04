package edu.sabanciuniv.model;

import jakarta.persistence.Entity;

@Entity
public class VisitingResearcher extends Instructor{

    private long hourlySalary;

    public VisitingResearcher(String name, String address, String phoneNumber, long hourlySalary) {
        super(name, address, phoneNumber);
        this.hourlySalary = hourlySalary;
    }
    public VisitingResearcher() {

    }

    public long getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(long hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

}
