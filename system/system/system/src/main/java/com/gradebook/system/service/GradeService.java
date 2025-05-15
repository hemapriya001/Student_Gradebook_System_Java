package com.gradebook.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradebook.system.model.Grade;
import com.gradebook.system.model.Student;
import com.gradebook.system.repository.GradeRepository;

@Service
public class GradeService {
    @Autowired 
    private GradeRepository gradeRepo;

    public Grade assignGrade(Grade g) {
        return gradeRepo.save(g);
    }

    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepo.findByStudentId(studentId);
    }

    public void deleteGrade(Long id) {
        gradeRepo.deleteById(id);
    }

    public Grade updateGrade(Long id, Grade g) {
        g.setId(id); // Ensures the grade object being saved has the correct ID.
        return gradeRepo.save(g);	
    }

}