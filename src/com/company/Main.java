package com.company;

import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        var filepath = "src\\input.csv";
        var studentsFromCSV = Parser.parseStudentsFromCSV(filepath);
        var vkData = VkParser.parse();
        var scanner = new Scanner(System.in);
        var students = joinStudentsAndVk(studentsFromCSV, vkData);
        showStudent(students);
//        var dbWorker = new DbWorker();
//        dbWorker.connect();
//        dbWorker.addVkRecord(new VkData(1245217, "wfewe", "qfqfqwf", "fwqfwq", "qwfqwf", "fqw", "fqwfq"));
//        dbWorker.close();
    }

    public static void showStudent(ArrayList students) {
        for (var s: students) {
            System.out.println(s.toString());
        }
    }

    public static ArrayList<Student> joinStudentsAndVk(ArrayList<Student> students, HashMap<String, VkData> dict) {
        for (var s: students) {{
                var vkData = dict.get(s.getFirstName() + " " + s.getLastName());
                s.setVkData(vkData);
            }
        }
        return students;
    }
}
