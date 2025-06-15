package codereview.school_mate.mapper;

import codereview.school_mate.dto.request.ScheduleRequestDto;
import codereview.school_mate.dto.responce.ScheduleResponseDto;
import codereview.school_mate.model.Schedule;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class, SchoolClassMapper.class, TeacherMapper.class, ClassroomMapper.class, WeekDayMapper.class, DailyTimeMapper.class})
public interface ScheduleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "schoolClasses", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "weekDay", ignore = true)
    @Mapping(target = "dailyTime", ignore = true)
    Schedule toEntity(ScheduleRequestDto dto);

    ScheduleResponseDto toDto(Schedule entity);

    List<ScheduleResponseDto> toDtos(List<Schedule> schedules);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "schoolClasses", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "weekDay", ignore = true)
    @Mapping(target = "dailyTime", ignore = true)
    void updateEntityFromDto(ScheduleRequestDto dto, @MappingTarget Schedule entity);
}
