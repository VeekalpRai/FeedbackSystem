package com.example.feedbacksystem;

public class User {
    private int id;
    private String rollnumber,semester,department;
    //Constructor to initialize varaibles
    public User(int id, String roll_no, String semester,String department){
        this.id = id;
        this.rollnumber = roll_no;
        this.semester = semester;
        this.department = department;
    }
    //Getter methods
    public int getId(){
        return id;
    }
    public String getRoll_no(){
        return rollnumber;
    }
    public String getSemester(){
        return semester;
    }

    public String getDepartment() {
        return department;
    }
}
