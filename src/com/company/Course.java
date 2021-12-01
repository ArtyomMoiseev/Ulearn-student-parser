package com.company;

public class Course {
    private String courseName;
    private String teacherName;
    private float score;
    private float lectureScore;
    private float practiceScore;

    public Course(String courseName, String teacherName, float score, float lectureScore, float practiceScore) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.score = score;
        this.lectureScore = lectureScore;
        this.practiceScore = practiceScore;
    }

    public Course(String courseName, String teacherName) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.score = 0;
        this.lectureScore = 0;
        this.practiceScore = 0;
    }

    @Override
    public String toString() {
        return this.courseName + ' ' + this.teacherName + " score:" + score;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getLectureScore() {
        return lectureScore;
    }

    public void setLectureScore(float lectureScore) {
        this.lectureScore = lectureScore;
    }

    public float getPracticeScore() {
        return practiceScore;
    }

    public void setPracticeScore(float practiceScore) {
        this.practiceScore = practiceScore;
    }

}
