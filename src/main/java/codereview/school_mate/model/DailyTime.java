package codereview.school_mate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
@Table(name = "daily_time")
public class DailyTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    //TODO Сделать LONG тип
    @Column(name = "lesson_number", nullable = false)
    private Long lessonNumber;
}
