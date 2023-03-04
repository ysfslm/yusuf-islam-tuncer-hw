package edu.sabanciuniv.model;

import jakarta.persistence.Entity;

@Entity
public class PermanentInstructor extends Instructor{

    private long fixedSalary;

    public PermanentInstructor(String name, String address, String phoneNumber, long fixedSalary) {
        super(name, address, phoneNumber);
        this.fixedSalary = fixedSalary;
    }
    public PermanentInstructor() {

    }

    public long getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(long fixedSalary) {
        this.fixedSalary = fixedSalary;
    }


}

