package com.company;

import java.util.ArrayList;

public class Student extends Human {
    private ArrayList<Theme> themes;
    private String groupCode;
    private int score;

    public Student(String firstName, String lastName, String groupCode, int score, ArrayList<Theme> themes) {
        super(firstName, lastName);
        this.groupCode = groupCode;
        this.score = score;
        this.themes = themes;
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
