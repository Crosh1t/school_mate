package codereview.school_mate.controller;

import codereview.school_mate.dto.ParentResponseDto;
import codereview.school_mate.service.ParentService;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(ParentController.class)
class ParentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParentService parentService;

    @Test
    void createParent_ValidRequest_ReturnsCreatedStatus() throws Exception {
        ParentResponseDto responseDto = new ParentResponseDto();

        when(parentService.create(any())).thenReturn(responseDto);

        mockMvc.perform(post("/api/parents")
                        .contentType(APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getParentById_ExistingId_ReturnsOkStatus() throws Exception {
        ParentResponseDto responseDto = new ParentResponseDto();

        when(parentService.findById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/api/parents/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getParentById_NonExistingId_ReturnsNotFoundStatus() throws Exception {
        when(parentService.findById(1L)).thenThrow(new RuntimeException("Parent not found with id: 1"));

        mockMvc.perform(get("/api/parents/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllParents_ParentsExist_ReturnsOkStatus() throws Exception {
        when(parentService.findAll()).thenReturn(List.of(new ParentResponseDto(), new ParentResponseDto()));

        mockMvc.perform(get("/api/parents"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllParents_NoParents_ReturnsOkStatusAndEmptyList() throws Exception {
        when(parentService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/parents"))
                .andExpect(status().isOk());
    }

    @Test
    void updateParent_ExistingId_ReturnsOkStatus() throws Exception {
        ParentResponseDto responseDto = new ParentResponseDto();

        when(parentService.update(eq(1L), any())).thenReturn(responseDto);

        mockMvc.perform(put("/api/parents/1")
                        .contentType(APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void updateParent_NonExistingId_ReturnsNotFoundStatus() throws Exception {
        when(parentService.update(eq(1L), any())).thenThrow(new RuntimeException("Parent not found with id: 1"));

        mockMvc.perform(put("/api/parents/1")
                        .contentType(APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteParent_ExistingId_ReturnsNoContentStatus() throws Exception {
        mockMvc.perform(delete("/api/parents/1"))
                .andExpect(status().isNoContent());

        verify(parentService).delete(1L);
    }
}