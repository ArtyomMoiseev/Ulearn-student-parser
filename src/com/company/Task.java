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

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    @Override
    public String toString() {
        return this.name + ' ' + this.score + '/' + this.maxScore;
    }
}
