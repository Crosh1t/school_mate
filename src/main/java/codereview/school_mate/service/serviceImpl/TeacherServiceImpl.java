package codereview.school_mate.service.serviceImpl;

import codereview.school_mate.dto.TeacherRequestDto;
import codereview.school_mate.dto.TeacherResponseDto;
import codereview.school_mate.mapper.TeacherMapper;
import codereview.school_mate.model.Teacher;
import codereview.school_mate.repository.TeacherRepository;
import codereview.school_mate.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    @Transactional
    public TeacherResponseDto create(TeacherRequestDto dto) {
        Teacher teacher = teacherMapper.toEntity(dto);
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    @Override
    public TeacherResponseDto findById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacherMapper.toDto(teacher);
    }

    @Override
    public List<TeacherResponseDto> findAll() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TeacherResponseDto update(Long id, TeacherRequestDto dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacherMapper.updateEntityFromDto(dto, teacher);
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TeacherResponseDto addSubjectToTeacher(Long teacherId, Long subjectId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
