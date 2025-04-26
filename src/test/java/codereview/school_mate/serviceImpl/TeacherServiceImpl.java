package codereview.school_mate.serviceImpl;

import codereview.school_mate.dto.TeacherRequestDto;
import codereview.school_mate.dto.TeacherResponseDto;
import codereview.school_mate.mapper.TeacherMapper;
import codereview.school_mate.model.Teacher;
import codereview.school_mate.repository.TeacherRepository;
import codereview.school_mate.service.serviceImpl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class TeacherServiceTest {

    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;
    private TeacherServiceImpl teacherService;

    @BeforeEach
    void setUp() {
        teacherRepository = mock(TeacherRepository.class);
        teacherMapper = mock(TeacherMapper.class);
        teacherService = new TeacherServiceImpl(teacherRepository, teacherMapper);
    }

    @Test
    void create_ValidRequest_ReturnsTeacherResponseDto() {
        TeacherRequestDto requestDto = new TeacherRequestDto();
        Teacher teacher = new Teacher();
        Teacher savedTeacher = new Teacher();
        TeacherResponseDto responseDto = new TeacherResponseDto();

        when(teacherMapper.toEntity(requestDto)).thenReturn(teacher);
        when(teacherRepository.save(teacher)).thenReturn(savedTeacher);
        when(teacherMapper.toDto(savedTeacher)).thenReturn(responseDto);

        TeacherResponseDto result = teacherService.create(requestDto);

        assertEquals(responseDto, result);
        verify(teacherRepository).save(teacher);
    }

    @Test
    void findById_ExistingId_ReturnsTeacherResponseDto() {
        Long id = 1L;
        Teacher teacher = new Teacher();
        TeacherResponseDto responseDto = new TeacherResponseDto();

        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toDto(teacher)).thenReturn(responseDto);

        TeacherResponseDto result = teacherService.findById(id);

        assertEquals(responseDto, result);
    }

    @Test
    void findById_NonExistingId_ThrowsRuntimeException() {
        Long id = 1L;

        when(teacherRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> teacherService.findById(id));
        assertEquals("Teacher not found", exception.getMessage());
    }

    @Test
    void findAll_TeachersExist_ReturnsListOfTeacherResponseDto() {
        List<Teacher> teachers = List.of(new Teacher(), new Teacher());
        List<TeacherResponseDto> responseDtos = List.of(new TeacherResponseDto(), new TeacherResponseDto());

        when(teacherRepository.findAll()).thenReturn(teachers);
        when(teacherMapper.toDto(teachers.get(0))).thenReturn(responseDtos.get(0));
        when(teacherMapper.toDto(teachers.get(1))).thenReturn(responseDtos.get(1));

        List<TeacherResponseDto> result = teacherService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void findAll_NoTeachers_ReturnsEmptyList() {
        when(teacherRepository.findAll()).thenReturn(List.of());

        List<TeacherResponseDto> result = teacherService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void update_ExistingIdAndValidRequest_ReturnsUpdatedTeacherResponseDto() {
        Long id = 1L;
        TeacherRequestDto requestDto = new TeacherRequestDto();
        Teacher teacher = new Teacher();
        TeacherResponseDto responseDto = new TeacherResponseDto();

        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(teacher)).thenReturn(teacher);
        when(teacherMapper.toDto(teacher)).thenReturn(responseDto);

        TeacherResponseDto result = teacherService.update(id, requestDto);

        assertEquals(responseDto, result);
        verify(teacherMapper).updateEntityFromDto(requestDto, teacher);
    }

    @Test
    void update_NonExistingId_ThrowsRuntimeException() {
        Long id = 1L;
        TeacherRequestDto requestDto = new TeacherRequestDto();

        when(teacherRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> teacherService.update(id, requestDto));
        assertEquals("Teacher not found", exception.getMessage());
    }

    @Test
    void delete_ExistingId_DeletesTeacher() {
        Long id = 1L;

        teacherService.delete(id);

        verify(teacherRepository).deleteById(id);
    }

    @Test
    void addSubjectToTeacher_NotImplemented_ThrowsUnsupportedOperationException() {
        Long teacherId = 1L;
        Long subjectId = 2L;

        assertThrows(UnsupportedOperationException.class, () -> teacherService.addSubjectToTeacher(teacherId, subjectId));
    }
}