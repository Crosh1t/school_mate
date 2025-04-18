package codereview.school_mate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

}
