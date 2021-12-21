package com.company;

import java.util.ArrayList;

public class Student extends Human {
    private ArrayList<CourseRecord> courses = new ArrayList<CourseRecord>();
    private VkData vkData;

    public Student(String firstName, String lastName, CourseRecord record) {
        super(firstName, lastName);
        this.courses.add(record);
    }

    public void setVkData(VkData data) {
        vkData = data;
    }

    @Override
    public String toString() {
        var courseRecord = courses.get(0);
        var themesBuilder = new StringBuilder();
        for (var t: courseRecord.getThemes()) {
            themesBuilder.append(t.toString() + '\n');
        }

        if (vkData != null) {
            return super.toString() + ' ' + courseRecord.getGroupCode() + ' ' + '\n' + vkData.toString();
        }
        else {
            return super.toString() + ' ' + courseRecord.getGroupCode();
        }
    }

    public CourseRecord getCourseRecord(String courseName) {
        for (var c: courses) {
            if (c.getCourseName().equals(courseName)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Course not exist");
    }

    public VkData getVkData() {
        return vkData;
    }

}
