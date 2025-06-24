package com.unrealconclusion.springboot.thymeleaf.model;

import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String favoriteLanguage;
    private List<String> favoriteOperatingSystems; 

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

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public String getFavoriteLanguage() {
        return this.favoriteLanguage;
    }

    public void setFavoriteOperatingSystems(List<String> favoriteOperatingSystems) {
        this.favoriteOperatingSystems = favoriteOperatingSystems;
    }

    public List<String> getFavoriteOperatingSystems() {
        return this.favoriteOperatingSystems;
    }
}
