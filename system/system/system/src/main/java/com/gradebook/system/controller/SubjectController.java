package com.gradebook.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gradebook.system.model.Subject;
import com.gradebook.system.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired 
    private SubjectService service;

    @PostMapping
    public Subject add(@RequestBody Subject s) { 
    	return service.addSubject(s); 
    	}
    
    @GetMapping 
    public List<Subject> getAll(@RequestParam Long teacherId) {
    	return service.getAll(teacherId); 
    	}
    
    @DeleteMapping("/{id}") 
    public void delete(@PathVariable Long id) { 
    	service.deleteSubject(id);
    	}
}