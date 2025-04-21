package codereview.school_mate.mapper;

import codereview.school_mate.dto.ParentRequestDto;
import codereview.school_mate.dto.ParentResponseDto;
import codereview.school_mate.model.Parent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "string")
public interface ParentMapper {
    Parent toEntity(ParentRequestDto dto);
    ParentResponseDto toDto(Parent entity);
    void updateEntityFromDto(ParentRequestDto dto, @MappingTarget Parent entity);
}
