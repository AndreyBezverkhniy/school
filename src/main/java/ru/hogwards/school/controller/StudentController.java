package ru.hogwards.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public long addStudent(@RequestBody Student student) {
        return studentService.createStudent(student).getId();
    }

    @PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody Student student) {
        if (student == null) {
            return ResponseEntity.badRequest().build();
        }
        Student newStudentValue = studentService.updateStudent(student);
        return ResponseEntity.ok(newStudentValue);
    }

    @GetMapping("age/{age}")
    public Collection<Student> getStudentsWithAge(@PathVariable int age) {
        return studentService.getStudentsWithAge(age);
    }

    @GetMapping("age")
    public Collection<Student> getStudentsWithAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getStudentsWithAgeBetween(min, max);
    }

    @GetMapping("{id}/faculty")
    public Faculty getFacultyOfStudentById(@PathVariable Long id) {
        return studentService.getFacultyOfStudentById(id);
    }
}
