
package codereview.school_mate.serviceImpl;

import codereview.school_mate.dto.StudentRequestDto;
import codereview.school_mate.dto.StudentResponseDto;
import codereview.school_mate.mapper.StudentMapper;
import codereview.school_mate.model.Student;
import codereview.school_mate.repository.StudentRepository;

import codereview.school_mate.service.serviceImpl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentMapper = mock(StudentMapper.class);
        studentService = new StudentServiceImpl(studentRepository, studentMapper);
    }

    @Test
    void create_ValidRequest_ReturnsStudentResponseDto() {
        // given
        StudentRequestDto requestDto = new StudentRequestDto();
        Student student = new Student();
        Student savedStudent = new Student();
        StudentResponseDto responseDto = new StudentResponseDto();

        when(studentMapper.toEntity(requestDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.studentToStudentResponseDto(savedStudent)).thenReturn(responseDto);

        // when
        StudentResponseDto result = studentService.create(requestDto);

        // then
        assertEquals(responseDto, result);
        verify(studentRepository).save(student);
    }

    @Test
    void findById_ExistingId_ReturnsStudentResponseDto() {
        // given
        Long id = 1L;
        Student student = new Student();
        StudentResponseDto responseDto = new StudentResponseDto();

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentMapper.studentToStudentResponseDto(student)).thenReturn(responseDto);

        // when
        StudentResponseDto result = studentService.findById(id);

        // then
        assertEquals(responseDto, result);
        verify(studentRepository).findById(id);
    }

    @Test
    void findById_NonExistingId_ThrowsException() {
        // given
        Long id = 1L;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        // when + then
        Exception exception = assertThrows(RuntimeException.class, () -> studentService.findById(id));
        assertEquals("Student not found with id: " + id, exception.getMessage());

        verify(studentRepository).findById(id);
    }

    @Test
    void create_NullEntity_ThrowsException() {
        // given
        StudentRequestDto requestDto = new StudentRequestDto();
        when(studentMapper.toEntity(requestDto)).thenReturn(null);

        // when + then
        Exception exception = assertThrows(RuntimeException.class, () -> studentService.create(requestDto));
        assertEquals("Failed to map StudentRequestDto to Student entity", exception.getMessage());
    }
}