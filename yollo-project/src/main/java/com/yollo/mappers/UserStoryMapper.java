package com.yollo.mappers;

import com.yollo.dtos.UserStoryPatchDTO;
import com.yollo.dtos.UserStoryRequestDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import com.yollo.models.UserStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface UserStoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target="tasks" , ignore = true)
    @Mapping(target="epic" , ignore = true)
    @Mapping(target="sprintBacklogs" , ignore = true)
    UserStory toEntity(UserStoryRequestDTO userStoryDTO);


    UserStoryResponseDTO toDTO(UserStory userStory);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target="tasks" , ignore = true)
    @Mapping(target="epic" , ignore = true)
    @Mapping(target="sprintBacklogs" , ignore = true)
    void updateUserStoryFromPatch(UserStoryPatchDTO patch, @MappingTarget UserStory entity);
}
