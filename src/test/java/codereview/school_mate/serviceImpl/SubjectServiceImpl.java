package codereview.school_mate.serviceImpl;

import codereview.school_mate.dto.SubjectRequestDto;
import codereview.school_mate.dto.SubjectResponseDto;
import codereview.school_mate.mapper.SubjectMapper;
import codereview.school_mate.model.Subject;
import codereview.school_mate.repository.SubjectRepository;
import codereview.school_mate.service.serviceImpl.SubjectServiceImpl;
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

class SubjectServiceTest {

    private SubjectRepository subjectRepository;
    private SubjectMapper subjectMapper;
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        subjectRepository = mock(SubjectRepository.class);
        subjectMapper = mock(SubjectMapper.class);
        subjectService = new SubjectServiceImpl(subjectRepository, subjectMapper);
    }

    @Test
    void create_ValidRequest_ReturnsSubjectResponseDto() {
        SubjectRequestDto requestDto = new SubjectRequestDto();
        Subject subject = new Subject();
        Subject savedSubject = new Subject();
        SubjectResponseDto responseDto = new SubjectResponseDto();

        when(subjectMapper.toEntity(requestDto)).thenReturn(subject);
        when(subjectRepository.save(subject)).thenReturn(savedSubject);
        when(subjectMapper.toDto(savedSubject)).thenReturn(responseDto);

        SubjectResponseDto result = subjectService.create(requestDto);

        assertEquals(responseDto, result);
        verify(subjectRepository).save(subject);
    }

    @Test
    void findById_ExistingId_ReturnsSubjectResponseDto() {
        Long id = 1L;
        Subject subject = new Subject();
        SubjectResponseDto responseDto = new SubjectResponseDto();

        when(subjectRepository.findById(id)).thenReturn(Optional.of(subject));
        when(subjectMapper.toDto(subject)).thenReturn(responseDto);

        SubjectResponseDto result = subjectService.findById(id);

        assertEquals(responseDto, result);
    }

    @Test
    void findById_NonExistingId_ThrowsRuntimeException() {
        Long id = 1L;

        when(subjectRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> subjectService.findById(id));
        assertEquals("Subject not found", exception.getMessage());
    }

    @Test
    void findAll_SubjectsExist_ReturnsListOfSubjectResponseDto() {
        List<Subject> subjects = List.of(new Subject(), new Subject());
        List<SubjectResponseDto> responseDtos = List.of(new SubjectResponseDto(), new SubjectResponseDto());

        when(subjectRepository.findAll()).thenReturn(subjects);
        when(subjectMapper.toDto(subjects.get(0))).thenReturn(responseDtos.get(0));
        when(subjectMapper.toDto(subjects.get(1))).thenReturn(responseDtos.get(1));

        List<SubjectResponseDto> result = subjectService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void findAll_NoSubjects_ReturnsEmptyList() {
        when(subjectRepository.findAll()).thenReturn(List.of());

        List<SubjectResponseDto> result = subjectService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void update_ExistingIdAndValidRequest_ReturnsUpdatedSubjectResponseDto() {
        Long id = 1L;
        SubjectRequestDto requestDto = new SubjectRequestDto();
        Subject subject = new Subject();
        SubjectResponseDto responseDto = new SubjectResponseDto();

        when(subjectRepository.findById(id)).thenReturn(Optional.of(subject));
        when(subjectRepository.save(subject)).thenReturn(subject);
        when(subjectMapper.toDto(subject)).thenReturn(responseDto);

        SubjectResponseDto result = subjectService.update(id, requestDto);

        assertEquals(responseDto, result);
        verify(subjectMapper).updateEntityFromDto(requestDto, subject);
    }

    @Test
    void update_NonExistingId_ThrowsRuntimeException() {
        Long id = 1L;
        SubjectRequestDto requestDto = new SubjectRequestDto();

        when(subjectRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> subjectService.update(id, requestDto));
        assertEquals("Subject not found", exception.getMessage());
    }

    @Test
    void delete_ExistingId_DeletesSubject() {
        Long id = 1L;

        subjectService.delete(id);

        verify(subjectRepository).deleteById(id);
    }
}