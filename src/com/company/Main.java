package com.company;

import org.jfree.chart.ui.ApplicationFrame;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws Exception {
        var filepath = "src\\input.csv";
        var studentsFromCSV = Parser.parseStudentsFromCSV(filepath, "Ulearn java");
        //showStudent(studentsFromCSV);
        var vkData = VkParser.parse();
        var data = joinStudentsAndVk(studentsFromCSV, vkData);
//        var students = joinStudentsAndVk(studentsFromCSV, vkData);
//        var dbWorker = new DbWorker();
//        for (var vk: students) {
//            DbWorker.addVkRecord(vk);
//        }
//        DbWorker.close();
        GraphicsBuilder.drawGraphByGender(data);
        GraphicsBuilder.drawGraphByCity(data);
    }

    public static void showStudent(ArrayList<Student> students) {
        for (var s: students) {
            System.out.println(s.toString());
            for (var cr: s.getCourseRecord("Ulearn java").toDb()) {
                System.out.println(cr);
            }
        }
    }

    public static ArrayList<Student> joinStudentsAndVk(ArrayList<Student> students, HashMap<String, VkData> dict) {
        for (var s: students) {{
               if (dict.get(s.getFirstName() + " " + s.getLastName()) != null) {
                   s.setVkData(dict.get(s.getFirstName() + " " + s.getLastName()));
               }
            }
        }
        return students;
    }
}
