package com.gradebook.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradebook.system.model.Teacher;
import com.gradebook.system.repository.TeacherRepository;

@Service
public class AuthService {

    @Autowired
    private TeacherRepository teacherRepo;

    public Teacher login(String email, String password) {
        Teacher teacher = teacherRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!teacher.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return teacher;
    }
}
