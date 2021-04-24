package com.team9.projectevaluationslotbooking;

public class Teacher {
    private String email;
    private String name;
    private String contact;
    private String branch;

    public Teacher() {
    }

    public Teacher(String email, String name, String contact, String branch) {
        this.email = email;
        this.name = name;
        this.contact = contact;
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
