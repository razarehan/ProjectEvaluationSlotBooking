package com.team9.projectevaluationslotbooking;

public class Project {
    private String projectName;
    private String slotRequested;
    private String student;
    private String teacher1;
    private String teacher2;
    private String teacher3;
    private String teacher4;
    private String timeAlloted;

    public Project() {
    }

    public Project(String projectName, String slotRequested, String student, String teacher1, String teacher2, String teacher3, String teacher4, String timeAlloted) {
        this.projectName = projectName;
        this.slotRequested = slotRequested;
        this.student = student;
        this.teacher1 = teacher1;
        this.teacher2 = teacher2;
        this.teacher3 = teacher3;
        this.teacher4 = teacher4;
        this.timeAlloted = timeAlloted;
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

    public String getTeacher1() {
        return teacher1;
    }

    public void setTeacher1(String teacher1) {
        this.teacher1 = teacher1;
    }

    public String getTeacher2() {
        return teacher2;
    }

    public void setTeacher2(String teacher2) {
        this.teacher2 = teacher2;
    }

    public String getTeacher3() {
        return teacher3;
    }

    public void setTeacher3(String teacher3) {
        this.teacher3 = teacher3;
    }

    public String getTeacher4() {
        return teacher4;
    }

    public void setTeacher4(String teacher4) {
        this.teacher4 = teacher4;
    }

    public String getTimeAlloted() {
        return timeAlloted;
    }

    public void setTimeAlloted(String timeAlloted) {
        this.timeAlloted = timeAlloted;
    }
}
