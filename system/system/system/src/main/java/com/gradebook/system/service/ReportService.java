package com.gradebook.system.service;



import com.gradebook.system.model.Grade;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.gradebook.system.model.Student;
import com.gradebook.system.repository.GradeRepository;
import com.gradebook.system.repository.StudentRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Service
public class ReportService {

    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private StudentRepository studentRepo;
    
    
    public Map<String, Object> getReportData(Long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        List<Grade> grades = gradeRepo.findByStudentId(studentId);

        Map<String, Object> report = new LinkedHashMap<>();
        report.put("studentName", student.getName());
        report.put("rollNo", student.getRollNo());
        report.put("class", student.getStudentClass());
        report.put("dob", student.getDob());

        List<Map<String, Object>> gradeList = new ArrayList<>();
        for (Grade g : grades) {
            Map<String, Object> gradeData = new LinkedHashMap<>();
            gradeData.put("subject", g.getSubject().getName());
            gradeData.put("score", g.getScore());
            gradeData.put("remarks", g.getRemarks());
            gradeList.add(gradeData);
        }

        report.put("grades", gradeList);
        return report;
    }

    

    public ByteArrayInputStream generateReport(Long studentId) throws IOException {
        Student student = studentRepo.findById(studentId).orElseThrow();
        List<Grade> grades = gradeRepo.findByStudentId(studentId);

        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(doc, out);
            doc.open();

            doc.add(new Paragraph("Report Card for: " + student.getName()));
            doc.add(new Paragraph("Roll No: " + student.getRollNo()));
            doc.add(new Paragraph("Class: " + student.getStudentClass()));
            doc.add(new Paragraph("Date of Birth: " + student.getDob().toString()));
            doc.add(new Paragraph(" ")); // Blank line

            PdfPTable table = new PdfPTable(3);
            table.addCell("Subject");
            table.addCell("Score");
            table.addCell("Remarks");

            for (Grade g : grades) {
                table.addCell(g.getSubject().getName());
                table.addCell(String.valueOf(g.getScore()));
                table.addCell(g.getRemarks());
            }

            doc.add(table);
        } catch (DocumentException e) {
            throw new IOException(e);
        } finally {
            doc.close();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
