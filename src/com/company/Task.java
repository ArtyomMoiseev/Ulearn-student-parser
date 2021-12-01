package com.company;

public class Task {
    private String name;
    private int score;
    private int maxScore;


    public Task(String name, int score, int maxScore) {
        this.name = name;
        this.score = score;
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return this.name + ' ' + this.score + '/' + this.maxScore;
    }
}
