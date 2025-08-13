package ru.hogwards.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        facultyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public long addFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty).getId();
    }

    @PutMapping
    public ResponseEntity<Faculty> putFaculty(@RequestBody Faculty faculty) {
        if (faculty == null) {
            return ResponseEntity.badRequest().build();
        }
        Faculty newFacultyValue = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(newFacultyValue);
    }

    @GetMapping("color/{color}")
    public Collection<Faculty> getFacultiesWithColor(@PathVariable String color) {
        return facultyService.getFacultiesWithColor(color);
    }

    @GetMapping("search")
    public Collection<Faculty> getFacultiesWithNameOrColor(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {
        return facultyService.getFacultyWithNameOrWithColor(name, color);
    }

    @GetMapping("{id}/students")
    public Collection<Student> getStudentsOfFacultyById(@PathVariable Long id) {
        return facultyService.getStudentsOfFacultyById(id);
    }
}
