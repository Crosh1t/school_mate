package codereview.school_mate.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import codereview.school_mate.enums.DayOfWeekEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {
    @NotNull(message = "Дата обязательна")
    private LocalDateTime date;
    private String description;
    @NotNull(message = "ID предмета обязателен")
    private Long subjectId;
    @NotNull(message = "Необходимо указать хотя бы один класс")
    private Set<Long> classIds;
    private Set<Long> teacherIds;
    private Long classroomId;
    private DayOfWeekEnum weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
}
