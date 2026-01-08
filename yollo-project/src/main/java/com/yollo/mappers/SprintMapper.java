package com.yollo.mappers;


import com.yollo.dtos.SprintPatchDTO;
import com.yollo.dtos.SprintRequestDTO;
import com.yollo.dtos.SprintResponseDTO;
import com.yollo.models.SprintBacklog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface SprintMapper {

    @Mapping(target="id", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    @Mapping(target="userStoriesSprint", ignore = true)
    @Mapping(target="productBacklog", ignore = true)
    SprintBacklog toEntity(SprintRequestDTO sprintBacklogDTO);

    SprintResponseDTO toDTO(SprintBacklog sprintBacklog);

    @Mapping(target="id", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    @Mapping(target="userStoriesSprint", ignore = true)
    @Mapping(target="productBacklog", ignore = true)
    void updateSprintFromPatch(SprintPatchDTO patch, @MappingTarget SprintBacklog entity);

}


