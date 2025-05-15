package com.gradebook.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradebook.system.model.Grade;
import com.gradebook.system.model.Student;
import com.gradebook.system.service.GradeService;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    @Autowired
    private GradeService service;

    @PostMapping
    public Grade assign(@RequestBody Grade g) { 
    	return service.assignGrade(g); 
    	}
    
    
    @GetMapping("/student/{id}") 
    public List<Grade> getGrades(@PathVariable Long id) {
    	return service.getGradesByStudent(id); 
    	}
    
    
    @PutMapping("/student/{id}") 
    public Grade update(@PathVariable Long id, @RequestBody Grade g) {
        return service.updateGrade(id, g); 
    }

    
    
    @DeleteMapping("/{id}") 
    public void delete(@PathVariable Long id) { 
    	service.deleteGrade(id); 
    	}
}
