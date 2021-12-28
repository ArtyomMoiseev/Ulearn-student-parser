package com.company;

import java.util.ArrayList;

public class Student extends Human {
    private ArrayList<Course> courses = new ArrayList<Course>();

    public Student(String firstName, String lastName, Course record) {
        super(firstName, lastName);
        this.courses.add(record);
    }


    @Override
    public String toString() {
        var courseRecord = courses.get(0);
        var themesBuilder = new StringBuilder();
        for (var t: courseRecord.getThemes()) {
            themesBuilder.append(t.toString() + '\n');
        }

        if (super.getVkId() != 0) {
            return super.toString() + ' ' + courseRecord.getGroupCode() + ' ' + '\n' + super.toString();
        }
        else {
            return super.toString() + ' ' + courseRecord.getGroupCode();
        }
    }

    public Course getCourseRecord(String courseName) {
        for (var c: courses) {
            if (c.getCourseName().equals(courseName)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Course not exist");
    }


}
