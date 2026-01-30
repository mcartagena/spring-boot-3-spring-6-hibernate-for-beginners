package com.mcartagena.demo.rest;

import com.mcartagena.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstructor to load the student data...  only once!

    @PostConstruct
    public void loadData() {
        theStudents = List.of(
                new Student("Poornima", "Patel"),
                new Student("Mario", "Rossi"),
                new Student("Mary", "Smith")
        );
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    // declare endpoint "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        // check the studentId again list size

        if (studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }

}
