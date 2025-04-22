package codereview.school_mate.service.serviceImpl;

import codereview.school_mate.dto.StudentRequestDto;
import codereview.school_mate.dto.StudentResponseDto;
import codereview.school_mate.mapper.StudentMapper;
import codereview.school_mate.model.Student;
import codereview.school_mate.repository.StudentRepository;
import codereview.school_mate.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentResponseDto create(StudentRequestDto dto) {
        Student student = studentMapper.toEntity(dto);
        Student saveStudent = studentRepository.save(student);
        return studentMapper.toDto(saveStudent);
    }

    @Override
    public StudentResponseDto findById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found with id: " + id));
        return studentMapper.toDto(student);
    }

    @Override
    public List<StudentResponseDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentResponseDto update(Long id, StudentRequestDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentMapper.updateEntityFromDto(dto, student);
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
