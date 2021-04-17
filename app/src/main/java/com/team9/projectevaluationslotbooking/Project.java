package com.team9.projectevaluationslotbooking;

public class Project {
    private String projectName;
    private String slotRequested;
    private String student;

    public Project() {
    }

    public Project(String projectName, String slotRequested, String student) {
        this.projectName = projectName;
        this.slotRequested = slotRequested;
        this.student = student;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSlotRequested() {
        return slotRequested;
    }

    public void setSlotRequested(String slotRequested) {
        this.slotRequested = slotRequested;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
