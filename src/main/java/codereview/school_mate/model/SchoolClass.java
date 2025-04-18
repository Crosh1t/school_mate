package codereview.school_mate.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "school_classes")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "class_teacher",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers;
}