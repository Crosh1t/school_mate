package codereview.school_mate.service;

import codereview.school_mate.dto.StudentRequestDto;
import codereview.school_mate.dto.StudentResponseDto;
import java.util.List;

public interface StudentService {
        StudentResponseDto create(StudentRequestDto dto);
        StudentResponseDto findById(Long id);
        List<StudentResponseDto> findAll();
        StudentResponseDto update(Long id, StudentRequestDto dto);
        void delete(Long id);
    }
