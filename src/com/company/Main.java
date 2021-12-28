package com.company;

public class Main{

    public static void main(String[] args) throws Exception {
        var filepath = "src\\input.csv";
        var studentsFromCSV = Parser.parseStudentsFromCSV(filepath, "Ulearn java");
        var students = VkParser.joinVkStudent(studentsFromCSV);
        var db = new DbWorker();
        db.connect("identifier.sqlite");
        db.dropTables();
        db.createTables();
        db.insertIntoTable(students);
        GraphicsBuilder.drawBarGraph(db.tasksByTheme(), "tasks by theme", "taskByTheme.png");
        GraphicsBuilder.drawBarGraph(db.answersByTheme(), "tasks by theme", "answerByTheme.png");
        GraphicsBuilder.drawGraphByGender(students);
        GraphicsBuilder.drawGraphByCity(students);
        db.close();
    }



}
