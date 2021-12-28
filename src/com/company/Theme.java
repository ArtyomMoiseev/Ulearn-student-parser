package com.company;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<String> scoresToComma() {
        var scores = new StringBuilder();
        var names = new StringBuilder();
        var maxScores = new StringBuilder();

        for (var score: tasks) {
            names.append(score.getName() + ";");
            scores.append(score.getScore() + ";");
            maxScores.append(score.getMaxScore() + ";");
        }

        var result = new ArrayList<String>();
        result.add(names.toString());
        result.add(maxScores.toString());
        result.add(scores.toString());
        return result;
    }

    public String getName() {
        return name;
    }

    public int getSolvedTasks() {
        var counter = 0;
        for (var t: tasks) {
            if (t.getScore() > 0 && !t.getName().contains("вопрос")) {
                counter++;
            }
        }
        return counter;
    }

    public int getTrueAnswersCounter() {
        var counter = 0;
        for (var t: tasks) {
            if (t.getScore() == t.getMaxScore() && t.getName().contains("вопрос")) {
                counter++;
            }
        }
        return counter;
    }

}