package codereview.school_mate.service;

import codereview.school_mate.dto.ParentRequestDto;
import codereview.school_mate.dto.ParentResponseDto;
import java.util.List;

public interface ParentService {
        ParentResponseDto create(ParentRequestDto dto);
        ParentResponseDto findById(Long id);
        List<ParentResponseDto> findAll();
        ParentResponseDto update(Long id, ParentRequestDto dto);
        void delete(Long id);
    }
