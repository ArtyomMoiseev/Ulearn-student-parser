package com.company;

import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        var filepath = "src\\input.csv";
        var studentsFromCSV = Parser.parseStudentsFromCSV(filepath);
//        var vkData = VkParser.parse();
//        var students = joinStudentsAndVk(studentsFromCSV, vkData);
        var dbWorker = new DbWorker();
//        for (var vk: students) {
//            DbWorker.addVkRecord(vk);
//        }
//        DbWorker.close();
        System.out.println(studentsFromCSV.get(3).getThemes().get(4).scoresToComma().get(0));
    }

    public static void showStudent(ArrayList students) {
        for (var s: students) {
            System.out.println(s.toString());
        }
    }

    public static ArrayList<VkData> joinStudentsAndVk(ArrayList<Student> students, HashMap<String, VkData> dict) {
        var clearVk = new ArrayList<VkData>();
        for (var s: students) {{
               if (dict.get(s.getFirstName() + " " + s.getLastName()) != null) {
                   clearVk.add(dict.get(s.getFirstName() + " " + s.getLastName()));
               }
            }
        }
        return clearVk;
    }
}
