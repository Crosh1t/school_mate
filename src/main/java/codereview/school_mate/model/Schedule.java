package codereview.school_mate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_day_id", nullable = false)
    private WeekDay weekDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_time_id", nullable = false)
    private DailyTime dailyTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "schedules_classes",
            joinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id")
    )
    private Set<SchoolClass> schoolClasses;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teachers_schedules",
            joinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    )
    private Set<Teacher> teachers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
}
