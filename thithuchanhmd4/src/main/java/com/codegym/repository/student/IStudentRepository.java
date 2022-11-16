package com.codegym.repository.student;

import com.codegym.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentReposity extends JpaRepository<Student, Long> {
}
