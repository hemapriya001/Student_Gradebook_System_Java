package com.gradebook.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradebook.system.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);
}
