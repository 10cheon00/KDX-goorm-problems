package com.kdx.problem10.service;

import com.kdx.problem10.dto.GradeDto;
import com.kdx.problem10.entity.StudentGrade;
import com.kdx.problem10.exception.CustomException;
import com.kdx.problem10.exception.ErrorCode;
import com.kdx.problem10.exception.InputRestriction;
import com.kdx.problem10.repository.StudentGradeRepository;

import java.util.List;

public class StudentGradeService {
    private final StudentGradeRepository studentGradeRepository;

    public StudentGradeService() {
        this.studentGradeRepository = new StudentGradeRepository();
    }

    public List<StudentGrade> getAll() {
        return studentGradeRepository.findAll();
    }

    public StudentGrade createGrades(String name, Integer grade) {
        int maxGrade = 6;
        int minGrade = 0;
        if (grade >= maxGrade) {
            throw new CustomException(
                    ErrorCode.ERROR,
                    "grade는 " + maxGrade + " 이상일 수 없습니다.",
                    InputRestriction.InputRestrictionBuilder.builder()
                            .setMaxGrade(maxGrade)
                            .build());
        }
        if (grade <= minGrade) {
            throw new CustomException(
                    ErrorCode.ERROR,
                    "grade는 " + minGrade + " 이하일 수 없습니다.",
                    InputRestriction.InputRestrictionBuilder.builder()
                            .setMinGrade(minGrade)
                            .build());
        }
        StudentGrade studentGrade = new StudentGrade(name, grade);
        return studentGradeRepository.save(studentGrade);
    }
}
