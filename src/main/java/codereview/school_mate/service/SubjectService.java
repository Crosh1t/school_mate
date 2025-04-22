package codereview.school_mate.service;

import codereview.school_mate.dto.SubjectRequestDto;
import codereview.school_mate.dto.SubjectResponseDto;
import java.util.List;

public interface SubjectService {
    SubjectResponseDto create(SubjectRequestDto dto);
    SubjectResponseDto findById(Long id);
    List<SubjectResponseDto> findAll();
    SubjectResponseDto update(Long id, SubjectRequestDto dto);
    void delete(Long id);
}