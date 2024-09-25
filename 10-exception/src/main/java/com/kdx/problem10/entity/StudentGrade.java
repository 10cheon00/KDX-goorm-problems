package com.kdx.problem10.entity;

import jakarta.persistence.*;

@Entity
public class StudentGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // todo: Student와 ManyToOne?
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
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
