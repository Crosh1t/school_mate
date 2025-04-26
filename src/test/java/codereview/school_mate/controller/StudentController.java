package codereview.school_mate.controller;

import codereview.school_mate.dto.StudentRequestDto;
import codereview.school_mate.dto.StudentResponseDto;
import codereview.school_mate.service.StudentService;
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

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_ValidRequest_ReturnsCreatedStudent() throws Exception {
        StudentRequestDto requestDto = new StudentRequestDto();
        StudentResponseDto responseDto = new StudentResponseDto();

        Mockito.when(studentService.create(any())).thenReturn(responseDto);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void findById_ExistingId_ReturnsStudent() throws Exception {
        StudentResponseDto responseDto = new StudentResponseDto();
        Long id = 1L;

        Mockito.when(studentService.findById(id)).thenReturn(responseDto);

        mockMvc.perform(get("/students/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void findAll_ReturnsListOfStudents() throws Exception {
        List<StudentResponseDto> responseDtos = List.of(new StudentResponseDto(), new StudentResponseDto());

        Mockito.when(studentService.findAll()).thenReturn(responseDtos);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDtos)));
    }

    @Test
    void update_ExistingId_ReturnsUpdatedStudent() throws Exception {
        StudentRequestDto requestDto = new StudentRequestDto();
        StudentResponseDto responseDto = new StudentResponseDto();
        Long id = 1L;

        Mockito.when(studentService.update(eq(id), any())).thenReturn(responseDto);

        mockMvc.perform(put("/students/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void delete_ExistingId_DeletesStudent() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/students/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(studentService).delete(id);
    }
}