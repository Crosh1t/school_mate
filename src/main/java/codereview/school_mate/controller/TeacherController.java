package codereview.school_mate.controller;

import codereview.school_mate.dto.TeacherRequestDto;
import codereview.school_mate.dto.TeacherResponseDto;
import codereview.school_mate.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TeacherResponseDto> create(@RequestBody TeacherRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> update(@PathVariable Long id, @RequestBody TeacherRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{teacherId}/subjects/{subjectId}")
    public ResponseEntity<TeacherResponseDto> addSubject(@PathVariable Long teacherId, @PathVariable Long subjectId) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.addSubjectToTeacher(teacherId, subjectId));
    }
}