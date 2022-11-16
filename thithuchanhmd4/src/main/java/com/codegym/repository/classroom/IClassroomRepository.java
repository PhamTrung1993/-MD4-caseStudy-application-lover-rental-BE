package com.codegym.repository.classroom;

import com.codegym.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassroomReposity extends JpaRepository<Classroom, Long> {
}
