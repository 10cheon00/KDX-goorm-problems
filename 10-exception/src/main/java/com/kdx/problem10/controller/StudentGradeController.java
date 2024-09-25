package com.kdx.problem10.controller;

import com.kdx.problem10.dto.GradeDto;
import com.kdx.problem10.entity.StudentGrade;
import com.kdx.problem10.response.ApiResponse;
import com.kdx.problem10.service.StudentGradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kdx.problem10.response.ResponseGenerator.makeResponse;

@RestController
public class StudentGradeController {
    private final StudentGradeService studentGradeService;

    public StudentGradeController(StudentGradeService studentGradeService) {
        this.studentGradeService = studentGradeService;
    }

    @GetMapping
    public ApiResponse<List<StudentGrade>> getAllGrades() {
        return makeResponse(studentGradeService.getAll());
    }

    @PostMapping
    public ApiResponse<List<StudentGrade>> createGrades(@RequestBody List<GradeDto> grades) {
        List<StudentGrade> savedStudentGrades = studentGradeService.createGrades(grades);
        return makeResponse(savedStudentGrades);
    }
}
