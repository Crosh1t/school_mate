package codereview.school_mate.mapper;

import codereview.school_mate.dto.TeacherRequestDto;
import codereview.school_mate.dto.TeacherResponseDto;
import codereview.school_mate.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher toEntity(TeacherRequestDto dto);
    TeacherResponseDto toDto(Teacher entity);
    void updateEntityFromDto(TeacherRequestDto dto, @MappingTarget Teacher entity);
}