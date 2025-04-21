package codereview.school_mate.mapper;

import codereview.school_mate.dto.StudentRequestDto;
import codereview.school_mate.dto.StudentResponseDto;
import codereview.school_mate.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "string")
public interface StudentMapper {
    Student toEntity(StudentRequestDto dto);
    StudentResponseDto toDto(Student entity);
    void updateEntityFromDto(StudentRequestDto dto, @MappingTarget Student entity);
}
