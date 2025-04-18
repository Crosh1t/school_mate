package codereview.school_mate.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;
}
