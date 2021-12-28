package com.company;

import java.util.Date;

public class Human {
    private String firstName;
    private String lastName;
    private String bigPhotoUrl = null;
    private String smallPhotoUrl = null;
    private String city = null;
    private String bDate = "";
    private int sex = 0;
    private int vkId = 0;


    public Human(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bDate = birthDate;
    }

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bDate = null;
    }

    public void setVkData(int vkId, String bigPhotoUrl, String smallPhotoUrl, String city, String bDate, int sex) {
        this.vkId = vkId;
        this.bigPhotoUrl = bigPhotoUrl;
        this.smallPhotoUrl = smallPhotoUrl;
        this.city = city;
        this.bDate = bDate;
        this.sex = sex;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getbDate() {
        return bDate;
    }
    public int getVkId() {
        return vkId;
    }
    public int getSex() {
        return sex;
    }
    public String getBigPhotoUrl() {return bigPhotoUrl;};
    public String getSmallPhotoUrl (){return smallPhotoUrl;};
    public String getCity() {
        return city;
    }


    @Override
    public String toString() {
        return this.firstName + ' ' + this.lastName + ' ' + this.bDate;
    }

}