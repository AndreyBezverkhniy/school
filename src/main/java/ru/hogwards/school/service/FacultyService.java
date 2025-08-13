package ru.hogwards.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findById(Long id) {
        return facultyRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultiesWithColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> getFacultyWithNameOrWithColor(String name, String color) {
        List<Faculty> facultiesFound = new ArrayList<Faculty>();
        if (name != null && !name.isBlank()) {
            List<Faculty> facultiesWithName = facultyRepository.findByNameIgnoreCase(name);
            facultiesWithName.addAll(facultiesFound);
            facultiesFound = facultiesWithName;
        }
        if (color != null && !color.isBlank()) {
            List<Faculty> facultiesWithColor = facultyRepository.findByColorIgnoreCase(color);
            facultiesWithColor.addAll(facultiesFound);
            facultiesFound = facultiesWithColor;
        }
        return facultiesFound;
    }

    public Collection<Student> getStudentsOfFacultyById(Long id) {
        return facultyRepository.findById(id).get().getStudents();
    }
}
