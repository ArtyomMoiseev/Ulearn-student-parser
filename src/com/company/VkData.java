package com.company;

public class VkData {
    private int id;
    private String name;
    private String lastName;
    private String bigPhotoUrl;
    private String smallPhotoUrl;
    private String city;
    private String bDate;
    private int sex = 0;


    public VkData(int id, String name, String lastName, int sex, String bigPhotoUrl, String smallPhotoUrl, String city, String bDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.bigPhotoUrl = bigPhotoUrl;
        this.smallPhotoUrl = smallPhotoUrl;
        this.city = city;
        this.bDate = bDate;
    }

    public int getId() {
        return id;
    }

    public String getBigPhotoUrl() {
        return bigPhotoUrl;
    }

    public int getSex() {
        return sex;
    }

    public String getCity() {
        return city;
    }

    public String getSmallPhotoUrl() {
        return smallPhotoUrl;
    }

    public String getbDate() {
        return bDate;
    }

    @Override
    public String toString() {
        return id + " " + sex + " " + bigPhotoUrl + " " + city + " " + bDate;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

}
