package codereview.school_mate.dto;

import codereview.school_mate.model.Subject;
import lombok.Data;
import java.util.Set;

@Data
public class TeacherResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Set<Subject> subjects;
}