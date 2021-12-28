package com.company;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;


abstract class Parser {

    public static ArrayList<Student> parseStudentsFromCSV(String path, String courseName) throws Exception {
        var result = new ArrayList<Student>();
        var fileReader = new FileReader(path);

        var csvParser = new CSVParserBuilder().withSeparator(';').build();
        var csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();
        var themes = csvReader.readNext();
        var tasks = csvReader.readNext();
        var tasksMax = csvReader.readNext();

        String[] s;
        while ((s = csvReader.readNext()) != null) {
            var name = s[0].split(" ");
            var groupCode = s[1];
            var score = Integer.parseInt(s[2]);

            result.add(new Student(name[1], name[0], new Course(courseName, groupCode, parseTheme(s, themes, tasks, tasksMax))));
        }
        return result;
    }

    private static ArrayList<Theme> parseTheme(String[] input, String[] themes, String[] tasks, String[] tasksMax) {
        var result = new ArrayList<Theme>();
        var themeName = "";
        var taskResult = new ArrayList<Task>();
        var allScore = 0;
        for (var i = 3; i < input.length; i++) {
            if (!themes[i].equals("") && themeName.equals("")) {
                themeName = themes[i];
                allScore = Integer.parseInt(input[i]);
            }

            if (themes[i].equals("")) {
                taskResult.add(new Task(tasks[i], Integer.parseInt(input[i]), Integer.parseInt(tasksMax[i])));
            }

            if (!themes[i].equals("") && !themeName.equals("")) {
                result.add(new Theme(themeName, allScore, taskResult));
                allScore = Integer.parseInt(input[i]);
                themeName = themes[i];
                taskResult = new ArrayList<Task>();
            }
        }
        return result;
    }
}
