package codereview.school_mate.controller;

import codereview.school_mate.dto.SubjectRequestDto;
import codereview.school_mate.dto.SubjectResponseDto;
import codereview.school_mate.service.SubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubjectController.class)
class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_ValidRequest_ReturnsCreatedSubject() throws Exception {
        SubjectRequestDto requestDto = new SubjectRequestDto();
        SubjectResponseDto responseDto = new SubjectResponseDto();

        Mockito.when(subjectService.create(any())).thenReturn(responseDto);

        mockMvc.perform(post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void findById_ExistingId_ReturnsSubject() throws Exception {
        SubjectResponseDto responseDto = new SubjectResponseDto();
        Long id = 1L;

        Mockito.when(subjectService.findById(id)).thenReturn(responseDto);

        mockMvc.perform(get("/subjects/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void findAll_ReturnsListOfSubjects() throws Exception {
        List<SubjectResponseDto> responseDtos = List.of(new SubjectResponseDto(), new SubjectResponseDto());

        Mockito.when(subjectService.findAll()).thenReturn(responseDtos);

        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDtos)));
    }

    @Test
    void update_ExistingId_ReturnsUpdatedSubject() throws Exception {
        SubjectRequestDto requestDto = new SubjectRequestDto();
        SubjectResponseDto responseDto = new SubjectResponseDto();
        Long id = 1L;

        Mockito.when(subjectService.update(eq(id), any())).thenReturn(responseDto);

        mockMvc.perform(put("/subjects/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void delete_ExistingId_DeletesSubject() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/subjects/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(subjectService).delete(id);
    }
}