package com.example.feedbacksystem;

public class ResponseHandler {
    String teacher_name,teacher_id,teacher_designation,teacher_image;

    public ResponseHandler() {
    }

    public ResponseHandler(String teacher_name, String teacher_id, String teacher_designation, String teacher_image) {
        this.teacher_name = teacher_name;
        this.teacher_id = teacher_id;
        this.teacher_designation = teacher_designation;
        this.teacher_image = teacher_image;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_designation() {
        return teacher_designation;
    }

    public void setTeacher_designation(String teacher_designation) {
        this.teacher_designation = teacher_designation;
    }

    public String getTeacher_image() {
        return teacher_image;
    }

    public void setTeacher_image(String teacher_image) {
        this.teacher_image = teacher_image;
    }
}
