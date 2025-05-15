package com.gradebook.system.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gradebook.system.service.ReportService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    
    
    @GetMapping("/{studentId}")
    public ResponseEntity<?> viewReport(@PathVariable Long studentId) {
        try {
            Map<String, Object> reportData = reportService.getReportData(studentId);
            return ResponseEntity.ok(reportData);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with ID: " + studentId);
        }
    }


    @GetMapping("/{studentId}/export")
    public ResponseEntity<InputStreamResource> export(@PathVariable Long studentId) throws IOException {
        ByteArrayInputStream stream = reportService.generateReport(studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }
}
