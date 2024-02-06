package com.example.college_scheduler_app;

public class Item {
    private String className;
    private String professor;
    private String section;
    private String roomNumber;
    private String repeatingDays;
    private String time;
    private String location;

    public String getRepeatingDays() {
        return repeatingDays;
    }

    public void setRepeatingDays(String repeatingDays) {
        this.repeatingDays = repeatingDays;
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

    public Item(String className, String professor, String section,
                String roomNumber, String repeatingDays, String time, String location) {
        this.className = className;
        this.professor = professor;
        this.section = section;
        this.roomNumber = roomNumber;
        this.repeatingDays = repeatingDays;
        this.time = time;
        this.location = location;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

}
