package com.example.college_scheduler_app;

public class CardModel {
    private String task;
    private String date;
    private String course;
    private String time;
    private String location;

    public CardModel(String task, String date, String course, String time, String location) {
        this.task = task;
        this.date = date;
        this.course = course;
        this.time = time;
        this.location = location;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
