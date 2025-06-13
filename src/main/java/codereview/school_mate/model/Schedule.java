package codereview.school_mate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Data
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "description", nullable = true)
    private StringBuilder description;

    @Column(name = "day_of_week", nullable = false)
    private Enum<DayOfWeek> dayOfWeekEnum;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Subject> subjects;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Set<SchoolClass> schoolClass;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Set<Teacher> teachers;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    @JsonIgnore
    private Classroom classroom;
}
