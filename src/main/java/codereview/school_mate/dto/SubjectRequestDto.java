package codereview.school_mate.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectRequestDto {
    @NotBlank(message = "Название предмета не может быть пустым")
    private String name;
}