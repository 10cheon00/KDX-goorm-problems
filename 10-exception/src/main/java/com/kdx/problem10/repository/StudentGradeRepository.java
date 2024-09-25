package com.kdx.problem10.repository;

import com.kdx.problem10.entity.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {
    List<StudentGrade> findAllByOrderByGradeAsc();
    Optional<StudentGrade> findGradeByGrade(Integer grade);
}
