package codereview.school_mate.dto;

import codereview.school_mate.model.Subject;
import codereview.school_mate.validation.Unique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequestDto {
    @NotBlank(message = "Название предмета не может быть пустым")
    @Size(min = 3, max = 100, message = "Название предмета должно быть от 3 до 100 символов")
    @Unique(
            entityClass = Subject.class,
            fieldName = "name",
            message = "Предмет с таким названием уже существует"
    )
    private String name;
}