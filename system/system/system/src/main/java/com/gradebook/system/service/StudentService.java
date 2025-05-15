package com.gradebook.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradebook.system.model.Student;
import com.gradebook.system.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired 
    private StudentRepository studentRepo;

    public Student addStudent(Student s) {
        return studentRepo.save(s);
    }

    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    public Student updateStudent(Long id, Student s) {
        s.setId(id);
        return studentRepo.save(s);
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
}