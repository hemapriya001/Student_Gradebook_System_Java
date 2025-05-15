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

import com.gradebook.system.model.Student;
import com.gradebook.system.service.StudentService;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired 
    private StudentService service;

    @PostMapping 
        public Student add(@RequestBody Student s) { 
    	return service.addStudent(s); 
    	}
    
    
    
    @GetMapping 
    public List<Student> getAll() { 
    	return service.getAll(); 
    	}
    
    @PutMapping("/{id}") 
    public Student update(@PathVariable Long id, @RequestBody Student s) {
    	return service.updateStudent(id, s); 
    	}
    
    @DeleteMapping("/{id}") 
    public void delete(@PathVariable Long id) { 
    	service.deleteStudent(id);
    	}
}