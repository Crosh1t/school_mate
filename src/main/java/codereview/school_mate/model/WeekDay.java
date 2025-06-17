package codereview.school_mate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "week_days")
public class WeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek; // MONDAY, TUESDAY ...
}
