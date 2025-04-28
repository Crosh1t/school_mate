package codereview.school_mate.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRequestDto {
    private String firstName;
    private String surname;
    private String patronymic;
    private String contacts;

}
