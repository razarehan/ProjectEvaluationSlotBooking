package com.team9.projectevaluationslotbooking;

public class Teacher {
    private String mail;
    private String name;
    private String contactNumber;

    public Teacher() {
    }

    public Teacher(String mail, String name, String contactNumber) {
        this.mail = mail;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
