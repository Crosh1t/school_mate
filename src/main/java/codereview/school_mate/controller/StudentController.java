package codereview.school_mate.controller;

import codereview.school_mate.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @GetMapping("/test")
    public Student testLombok() {
        return new Student(1L, "Alice", "Smith", 16);
    }
}