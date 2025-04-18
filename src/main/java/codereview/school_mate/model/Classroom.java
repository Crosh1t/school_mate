package codereview.school_mate.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;  // Номер аудитории (например, "101")
}