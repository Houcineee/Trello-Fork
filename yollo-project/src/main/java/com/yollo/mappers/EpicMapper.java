package com.yollo.mappers;


import com.yollo.dtos.EpicPatchDTO;
import com.yollo.dtos.EpicRequestDTO;
import com.yollo.dtos.EpicResponseDTO;
import com.yollo.models.Epic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface EpicMapper {

    @Mapping(target ="id", ignore = true)
    @Mapping(target ="productBacklog", ignore = true)
    @Mapping(target="userStories", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    Epic toEntity(EpicRequestDTO epicRequestDTO);

    @Mapping(target = "productBacklogId", source = "productBacklog.id")
    EpicResponseDTO toDTO(Epic epic);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productBacklog", ignore = true)
    @Mapping(target = "userStories", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    void updateEpicFromPatch(EpicPatchDTO patch, @MappingTarget Epic entity);

}
