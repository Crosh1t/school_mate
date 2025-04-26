package codereview.school_mate.controller;

import codereview.school_mate.dto.TeacherRequestDto;
import codereview.school_mate.dto.TeacherResponseDto;
import codereview.school_mate.service.TeacherService;
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

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_ValidRequest_ReturnsCreatedTeacher() throws Exception {
        TeacherRequestDto requestDto = new TeacherRequestDto();
        TeacherResponseDto responseDto = new TeacherResponseDto();

        Mockito.when(teacherService.create(any())).thenReturn(responseDto);

        mockMvc.perform(post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void findById_ExistingId_ReturnsTeacher() throws Exception {
        TeacherResponseDto responseDto = new TeacherResponseDto();
        Long id = 1L;

        Mockito.when(teacherService.findById(id)).thenReturn(responseDto);

        mockMvc.perform(get("/teachers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void findAll_ReturnsListOfTeachers() throws Exception {
        List<TeacherResponseDto> responseDtos = List.of(new TeacherResponseDto(), new TeacherResponseDto());

        Mockito.when(teacherService.findAll()).thenReturn(responseDtos);

        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDtos)));
    }

    @Test
    void update_ExistingId_ReturnsUpdatedTeacher() throws Exception {
        TeacherRequestDto requestDto = new TeacherRequestDto();
        TeacherResponseDto responseDto = new TeacherResponseDto();
        Long id = 1L;

        Mockito.when(teacherService.update(eq(id), any())).thenReturn(responseDto);

        mockMvc.perform(put("/teachers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void delete_ExistingId_DeletesTeacher() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/teachers/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(teacherService).delete(id);
    }
}