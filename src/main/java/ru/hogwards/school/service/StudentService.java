package ru.hogwards.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsWithAge(int age) {
        return studentRepository.findByAge(age);
    }
}
