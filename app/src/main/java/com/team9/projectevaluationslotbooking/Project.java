package com.team9.projectevaluationslotbooking;

public class Project {
    private String projectName;
    private String slotRequested;

    public Project() {
    }

    public Project(String projectName, String slotRequested) {
        this.projectName = projectName;
        this.slotRequested = slotRequested;
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
}
