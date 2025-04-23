package codereview.school_mate.service.serviceImpl;

import codereview.school_mate.dto.StudentRequestDto;
import codereview.school_mate.dto.StudentResponseDto;
import codereview.school_mate.mapper.StudentMapper;
import codereview.school_mate.model.Student;
import codereview.school_mate.repository.StudentRepository;
import codereview.school_mate.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponseDto create(StudentRequestDto dto) {
        Student student = studentMapper.toEntity(dto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.studentToStudentResponseDto(savedStudent);
    }

    @Override
    public StudentResponseDto findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return studentMapper.studentToStudentResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> findAll() {
        return studentMapper.studentsToStudentResponseDtos(studentRepository.findAll());
    }

    @Override
    public StudentResponseDto update(Long id, StudentRequestDto dto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentMapper.updateEntityFromDto(dto, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.studentToStudentResponseDto(updatedStudent);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}