package com.kdx.problem10.service;

import com.kdx.problem10.dto.GradeDto;
import com.kdx.problem10.entity.StudentGrade;
import com.kdx.problem10.exception.CustomException;
import com.kdx.problem10.exception.ErrorCode;
import com.kdx.problem10.exception.InputRestriction;
import com.kdx.problem10.repository.StudentGradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGradeService {
    private final StudentGradeRepository studentGradeRepository;

    public StudentGradeService(StudentGradeRepository studentGradeRepository) {
        this.studentGradeRepository = studentGradeRepository;
    }

    public List<StudentGrade> getAll() {
        return studentGradeRepository.findAll();
    }

    public List<StudentGrade> createGrades(List<GradeDto> grades) {
        int maxGrade = 6;
        int minGrade = 0;
        grades.stream().forEach(gradeDto -> {
            if (gradeDto.grade() >= maxGrade) {
                throw new CustomException(
                        ErrorCode.ERROR,
                        "grade는 " + maxGrade + " 이상일 수 없습니다.",
                        InputRestriction.InputRestrictionBuilder.builder()
                                .setMaxGrade(maxGrade)
                                .build());
            }
            if (gradeDto.grade() <= minGrade) {
                throw new CustomException(
                        ErrorCode.ERROR,
                        "grade는 " + minGrade + " 이하일 수 없습니다.",
                        InputRestriction.InputRestrictionBuilder.builder()
                                .setMinGrade(minGrade)
                                .build());
            }
        });
        return studentGradeRepository.saveAll(grades.stream().map(dto -> new StudentGrade(dto.name(), dto.grade())).toList());
    }
}
