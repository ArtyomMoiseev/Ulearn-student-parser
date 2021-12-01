package com.company;

public class Human {
    private String firstName;
    private String lastName;
    private int birthYear;
    private VkData vkData;

    public void setVkData(VkData data) {
        vkData = data;
    }

    public Human(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = 0;
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

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String toString() {
        return this.firstName + ' ' + this.lastName + ' ' + this.birthYear;
    }

    public VkData getVkData() {
        return vkData;
    }
}