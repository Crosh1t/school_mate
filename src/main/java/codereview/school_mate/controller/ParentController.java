package codereview.school_mate.controller;

import codereview.school_mate.dto.ParentRequestDto;
import codereview.school_mate.dto.ParentResponseDto;
import codereview.school_mate.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor

public class ParentController {
    private final ParentService parentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParentResponseDto create(@RequestBody ParentRequestDto dto) {
        return parentService.create(dto);
    }

    @GetMapping("/{id}")
    public ParentResponseDto findById(@PathVariable Long id) {
        return parentService.findById(id);
    }

    @GetMapping
    public List<ParentResponseDto> findAll() {
        return parentService.findAll();
    }

    @PutMapping("/{id}")
    public ParentResponseDto update(@PathVariable Long id, @RequestBody ParentRequestDto dto) {
        return parentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        parentService.delete(id);
    }
}
