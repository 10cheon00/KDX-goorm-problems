package com.kdx.problem10.repository;

import com.kdx.problem10.entity.StudentGrade;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StudentGradeRepository {
    private Set<StudentGrade> studentGradeSet;

    public StudentGradeRepository() {
        this.studentGradeSet = new HashSet<>();
    }

    public List<StudentGrade> findAll() {
        return studentGradeSet.stream().toList();
    }

    public List<StudentGrade> saveAll(List<StudentGrade> list) {
        studentGradeSet.addAll(list);
        return list;
    }

    public StudentGrade save(StudentGrade studentGrade) {
        studentGradeSet.add(studentGrade);
        return studentGrade;
    }
}
