package com.company;

import java.util.ArrayList;

public class CourseRecord {
    private String courseName;
    private String groupCode;
    private ArrayList<Theme> themes;

    public CourseRecord(String courseName, String groupCode, ArrayList<Theme> themes) {
        this.courseName = courseName;
        this.groupCode = groupCode;
        this.themes = themes;
    }

}
