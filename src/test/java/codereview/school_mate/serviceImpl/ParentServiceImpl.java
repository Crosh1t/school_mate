package codereview.school_mate.serviceImpl;

import codereview.school_mate.dto.ParentRequestDto;
import codereview.school_mate.dto.ParentResponseDto;
import codereview.school_mate.mapper.ParentMapper;
import codereview.school_mate.model.Parent;
import codereview.school_mate.repository.ParentRepository;
import codereview.school_mate.service.serviceImpl.ParentServiceImpl;
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

class ParentServiceTest {

    private ParentRepository parentRepository;
    private ParentMapper parentMapper;
    private ParentServiceImpl parentService;

    @BeforeEach
    void setUp() {
        parentRepository = mock(ParentRepository.class);
        parentMapper = mock(ParentMapper.class);
        parentService = new ParentServiceImpl(parentRepository, parentMapper);
    }

    @Test
    void create_ValidRequest_ReturnsParentResponseDto() {
        ParentRequestDto requestDto = new ParentRequestDto();
        Parent parent = new Parent();
        Parent savedParent = new Parent();
        ParentResponseDto responseDto = new ParentResponseDto();

        when(parentMapper.toEntity(requestDto)).thenReturn(parent);
        when(parentRepository.save(parent)).thenReturn(savedParent);
        when(parentMapper.toDto(savedParent)).thenReturn(responseDto);

        ParentResponseDto result = parentService.create(requestDto);

        assertEquals(responseDto, result);
        verify(parentRepository).save(parent);
    }

    @Test
    void findById_ExistingId_ReturnsParentResponseDto() {
        Long id = 1L;
        Parent parent = new Parent();
        ParentResponseDto responseDto = new ParentResponseDto();

        when(parentRepository.findById(id)).thenReturn(Optional.of(parent));
        when(parentMapper.toDto(parent)).thenReturn(responseDto);

        ParentResponseDto result = parentService.findById(id);

        assertEquals(responseDto, result);
    }

    @Test
    void findById_NonExistingId_ThrowsRuntimeException() {
        Long id = 1L;
        when(parentRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> parentService.findById(id));
        assertEquals("Parent not found with id: " + id, exception.getMessage());
    }

    @Test
    void findAll_ParentsExist_ReturnsListOfParentResponseDto() {
        List<Parent> parents = List.of(new Parent(), new Parent());
        List<ParentResponseDto> responseDtos = List.of(new ParentResponseDto(), new ParentResponseDto());

        when(parentRepository.findAll()).thenReturn(parents);
        when(parentMapper.toDtos(parents)).thenReturn(responseDtos);

        List<ParentResponseDto> result = parentService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void findAll_NoParents_ReturnsEmptyList() {
        when(parentRepository.findAll()).thenReturn(List.of());
        when(parentMapper.toDtos(List.of())).thenReturn(List.of());

        List<ParentResponseDto> result = parentService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void update_ExistingIdAndValidRequest_ReturnsUpdatedParentResponseDto() {
        Long id = 1L;
        ParentRequestDto requestDto = new ParentRequestDto();
        Parent parent = new Parent();
        ParentResponseDto responseDto = new ParentResponseDto();

        when(parentRepository.findById(id)).thenReturn(Optional.of(parent));
        when(parentRepository.save(parent)).thenReturn(parent);
        when(parentMapper.toDto(parent)).thenReturn(responseDto);

        ParentResponseDto result = parentService.update(id, requestDto);

        assertEquals(responseDto, result);
        verify(parentMapper).updateEntityFromDto(requestDto, parent);
    }

    @Test
    void update_NonExistingId_ThrowsRuntimeException() {
        Long id = 1L;
        ParentRequestDto requestDto = new ParentRequestDto();

        when(parentRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> parentService.update(id, requestDto));
        assertEquals("Parent not found with id: " + id, exception.getMessage());
    }

    @Test
    void delete_ExistingId_DeletesParent() {
        Long id = 1L;

        parentService.delete(id);

        verify(parentRepository).deleteById(id);
    }
}
