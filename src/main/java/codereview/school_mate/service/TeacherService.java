package codereview.school_mate.service;

import codereview.school_mate.dto.TeacherRequestDto;
import codereview.school_mate.dto.TeacherResponseDto;
import java.util.List;

public interface TeacherService {
    TeacherResponseDto create(TeacherRequestDto dto);
    TeacherResponseDto findById(Long id);
    List<TeacherResponseDto> findAll();
    TeacherResponseDto update(Long id, TeacherRequestDto dto);
    void delete(Long id);
    TeacherResponseDto addSubjectToTeacher(Long teacherId, Long subjectId);
}
