package codereview.school_mate.controller;

import codereview.school_mate.dto.ParentRequestDto;
import codereview.school_mate.dto.ParentResponseDto;
import codereview.school_mate.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ParentResponseDto> create(@RequestBody ParentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parentService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(parentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParentResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(parentService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentResponseDto> update(@PathVariable Long id, @RequestBody ParentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(parentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        parentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
