package com.yollo.mappers;


import com.yollo.dtos.TaskPatchDTO;
import com.yollo.dtos.TaskRequestDTO;
import com.yollo.dtos.TaskResponseDTO;
import com.yollo.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface TaskMapper {

    @Mapping(target="id", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    @Mapping(target="userStory", ignore = true)
    @Mapping(target="developer", ignore = true)
    @Mapping(target="tester", ignore = true)
    Task toEntity(TaskRequestDTO taskRequestDTO);

    TaskResponseDTO toDTO(Task task);

    @Mapping(target="id", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    @Mapping(target="userStory", ignore = true)
    @Mapping(target="developer", ignore = true)
    @Mapping(target="tester", ignore = true)
    void updateTaskFromPatch(TaskPatchDTO patch, @MappingTarget Task entity);


}
