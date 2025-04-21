package codereview.school_mate.controller;

import codereview.school_mate.dto.StudentRequestDto;
import codereview.school_mate.dto.StudentResponseDto;
import codereview.school_mate.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto create(@RequestBody StudentRequestDto dto) {
        return studentService.create(dto);
    }

    @GetMapping("/{id}")
    public StudentResponseDto findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping
    public List<StudentResponseDto> findAll() {
        return studentService.findAll();
    }

    @PutMapping("/{id}")
    public StudentResponseDto update(@PathVariable Long id, @RequestBody StudentRequestDto dto) {
        return studentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}