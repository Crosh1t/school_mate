package codereview.school_mate.dto;
import codereview.school_mate.model.Student;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParentResponseDto {
    private Long id;
    private String firstName;
    private String surname;
    private String patronymic;
    private String contacts;
    private List<Student> children= new ArrayList<>();
}
