package com.unrealconclusion.springboot.thymeleaf.model;

public class Student {
    private String firstName;
    private String lastName;
    private String country;

    public Student() {};

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 
    
    public String getLastName() {
        return this.lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }
}
