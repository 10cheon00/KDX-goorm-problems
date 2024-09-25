package com.kdx.problem10.entity;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public Student() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
