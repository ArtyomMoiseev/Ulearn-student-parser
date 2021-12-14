package com.company;

import java.util.ArrayList;

public class Student extends Human {
    private ArrayList<CourseRecord> courses;
    private int id;

    public Student(String firstName, String lastName, CourseRecord record, int id) {
        super(firstName, lastName);
        this.courses.add(record);
        this.id = id;
    }

    public void setVkData(VkData data) {
        super.setVkData(data);
    }

    @Override
    public String toString() {
        var themesBuilder = new StringBuilder();
        for (var t: themes) {
            themesBuilder.append(t.toString() + '\n');
        }

        if (super.getVkData() != null) {
            return super.toString() + ' ' + groupCode + ' ' + score + '\n' + super.getVkData().toString();
        }
        else {
            return super.toString() + ' ' + groupCode + ' ' + score;
        }
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

}
