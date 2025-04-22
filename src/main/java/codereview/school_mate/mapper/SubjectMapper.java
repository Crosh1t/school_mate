package codereview.school_mate.mapper;

import codereview.school_mate.dto.SubjectRequestDto;
import codereview.school_mate.dto.SubjectResponseDto;
import codereview.school_mate.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject toEntity(SubjectRequestDto dto);
    SubjectResponseDto toDto(Subject entity);
    void updateEntityFromDto(SubjectRequestDto dto, @MappingTarget Subject entity);
}