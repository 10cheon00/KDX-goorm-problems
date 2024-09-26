package com.kdx.problem10.controller;

import com.kdx.problem10.dto.GradeDto;
import com.kdx.problem10.entity.StudentGrade;
import com.kdx.problem10.response.ApiResponse;
import com.kdx.problem10.service.StudentGradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kdx.problem10.response.ResponseGenerator.makeResponse;

@RestController
@RequestMapping("/student")
public class StudentGradeController {
    private final StudentGradeService studentGradeService;

    public StudentGradeController() {
        this.studentGradeService = new StudentGradeService();
    }

    @GetMapping
    public ApiResponse<List<StudentGrade>> getAllGrades() {
        return makeResponse(studentGradeService.getAll());
    }

    @GetMapping("/{name}/{grade}")
    public ApiResponse<List<StudentGrade>> createGrades(
            @PathVariable("name") String name,
            @PathVariable("grade") Integer grade
    ) {
        return makeResponse(studentGradeService.createGrades(name, grade));
    }
}
