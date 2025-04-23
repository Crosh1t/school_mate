package codereview.school_mate.mapper;

import codereview.school_mate.dto.StudentRequestDto;
import codereview.school_mate.dto.StudentResponseDto;
import codereview.school_mate.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentRequestDto dto);
    StudentResponseDto studentToStudentResponseDto(Student entity);
    List<StudentResponseDto> studentsToStudentResponseDtos(List<Student> students);
    void updateEntityFromDto(StudentRequestDto dto, @MappingTarget Student entity);
}