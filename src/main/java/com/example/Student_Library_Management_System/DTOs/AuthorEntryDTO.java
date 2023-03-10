package com.example.Student_Library_Management_System.DTOs;

//this is just an object that is used to take request from postman
// It will contain the parameters that we want to send from postman

public class AuthorEntryDTO {
   // id is not here because we don't want to send it from postman

    private String name;
    private int age;
    private String country;
    private double rating;

    public AuthorEntryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
