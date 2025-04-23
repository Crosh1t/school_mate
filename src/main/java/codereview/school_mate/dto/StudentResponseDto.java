package codereview.school_mate.dto;
import codereview.school_mate.model.Parent;
import codereview.school_mate.model.SchoolClass;
import lombok.Data;

@Data
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String surname;
    private String patronymic;
    private SchoolClass schoolClass;
    private Parent parent;
}