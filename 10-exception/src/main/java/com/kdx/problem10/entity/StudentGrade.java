package com.kdx.problem10.entity;

public class StudentGrade {
    private String name;
    private Integer grade;

    public StudentGrade() {}

    public StudentGrade(String name, Integer grade) {
        this.name = name;
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public Integer getGrade() {
        return grade;
    }
}
