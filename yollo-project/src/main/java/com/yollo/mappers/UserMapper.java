package com.yollo.mappers;


import com.yollo.dtos.UserPatchDTO;
import com.yollo.dtos.UserRequestDTO;
import com.yollo.dtos.UserResponseDTO;
import com.yollo.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    @Mapping(target="testTask", ignore = true)
    @Mapping(target = "devTask", ignore = true)
    @Mapping(target="productBacklogs", ignore = true)
    User toEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toDTO(User user) ;


    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    @Mapping(target="testTask", ignore = true)
    @Mapping(target = "devTask", ignore = true)
    @Mapping(target="productBacklogs", ignore = true)
    @Mapping(target="password", ignore = true)
    void updateUserFromDTO(UserPatchDTO userPatchDTO, @MappingTarget User user);




}
