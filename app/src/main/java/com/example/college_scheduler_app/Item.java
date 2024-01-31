package com.example.college_scheduler_app;

public class Item {
    private String className;
    private String professor;
    private String section;
    private String roomNumber;


    public Item(String className, String professor, String section, String roomNumber) {
        this.className = className;
        this.professor = professor;
        this.section = section;
        this.roomNumber = roomNumber;
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
