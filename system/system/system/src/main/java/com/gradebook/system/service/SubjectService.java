package com.gradebook.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradebook.system.model.Subject;
import com.gradebook.system.repository.SubjectRepository;

@Service
public class SubjectService {
    @Autowired 
    private SubjectRepository subjectRepo;

    public Subject addSubject(Subject s) {
        return subjectRepo.save(s);
    }

    public List<Subject> getAll(Long teacherId) {
        return subjectRepo.findByTeacherId(teacherId);
    }

    public void deleteSubject(Long id) {
        subjectRepo.deleteById(id);
    }
}