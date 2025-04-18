package codereview.school_mate.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.security.auth.Subject;
import java.util.Set;

@Entity
@Data
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @ManyToMany
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;


    @ManyToMany(mappedBy = "teachers")
    private Set<SchoolClass> classes;
}