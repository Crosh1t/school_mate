package codereview.school_mate.controller;

import codereview.school_mate.dto.TeacherRequestDto;
import codereview.school_mate.dto.TeacherResponseDto;
import codereview.school_mate.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponseDto create(@RequestBody TeacherRequestDto dto) {
        return teacherService.create(dto);
    }

    @GetMapping("/{id}")
    public TeacherResponseDto findById(@PathVariable Long id) {
        return teacherService.findById(id);
    }

    @GetMapping
    public List<TeacherResponseDto> findAll() {
        return teacherService.findAll();
    }

    @PutMapping("/{id}")
    public TeacherResponseDto update(@PathVariable Long id, @RequestBody TeacherRequestDto dto) {
        return teacherService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }

    @PostMapping("/{teacherId}/subjects/{subjectId}")
    public TeacherResponseDto addSubject(@PathVariable Long teacherId, @PathVariable Long subjectId) {
        return teacherService.addSubjectToTeacher(teacherId, subjectId);
    }
}