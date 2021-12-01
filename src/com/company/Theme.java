package com.company;
import java.util.ArrayList;

public class Theme {
    private String name;
    private int allScore;
    private ArrayList<Task> tasks = new ArrayList();

    public Theme(String name, int allScore, ArrayList<Task> tasks) {
        this.name = name;
        this.allScore = allScore;
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append(name + " all score:" + this.allScore + '\n');
        for (var t: tasks) {
            builder.append(t.toString() + "; ");
        }
        return builder.toString();
    }

}