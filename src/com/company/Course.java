package com.company;

import java.util.ArrayList;
import java.util.Locale;

public class Course {
    private String courseName;
    private String groupCode;
    private ArrayList<Theme> themes;

    public Course(String courseName, String groupCode, ArrayList<Theme> themes) {
        this.courseName = courseName;
        this.groupCode = groupCode;
        this.themes = themes;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    @Override
    public String toString() {
        var resultBuilder = new StringBuilder();
        for (var t: themes) {
            resultBuilder.append(t.toString());
            resultBuilder.append("\n");
        }
        return resultBuilder.toString();
    }

    public ArrayList<String> toDb() {
        var themesNames = new StringBuilder();
        var themesLength = new StringBuilder();
        var scores = new StringBuilder();
        var names = new StringBuilder();
        var maxScores = new StringBuilder();
        for (var theme: themes) {
            themesNames.append(theme.getName() + ";");
            themesLength.append(theme.getTasks().size() + ";");
            var commaTheme = theme.scoresToComma();
            names.append(commaTheme.get(0));
            maxScores.append(commaTheme.get(1));
            scores.append(commaTheme.get(2));
        }
        var result = new ArrayList<String>();
        result.add( themesNames.toString());
        result.add(themesLength.toString());
        result.add(scores.toString());
        result.add(names.toString());
        result.add(maxScores.toString());
        return result;
    }

}
