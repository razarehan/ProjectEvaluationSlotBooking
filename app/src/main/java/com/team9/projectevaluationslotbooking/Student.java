package com.team9.projectevaluationslotbooking;

public class Student {
    private String fname, lname;
    private String sex;
    private String phone_number;
    private String branch;
    private String rollNumber;
    private String email;

    public Student(String fname, String lname, String sex, String phone_number, String branch, String rollNumber, String email) {
        this.fname = fname;
        this.lname = lname;
        this.sex = sex;
        this.phone_number = phone_number;
        this.branch = branch;
        this.rollNumber = rollNumber;
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
