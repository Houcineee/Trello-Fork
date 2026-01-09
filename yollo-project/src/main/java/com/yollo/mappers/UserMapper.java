package com.yollo.mappers;


import com.yollo.dtos.UserPatchDTO;
import com.yollo.dtos.UserRequestDTO;
import com.yollo.dtos.UserResponseDTO;
import com.yollo.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

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
    @Mapping(target="password", ignore = true)
    @Mapping(target="email", source = "email" , qualifiedByName = "toLowerCase")
    @Mapping(target="username", source = "username" , qualifiedByName = "toLowerCase")
    User toEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toDTO(User user) ;


    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    @Mapping(target="testTask", ignore = true)
    @Mapping(target = "devTask", ignore = true)
    @Mapping(target="productBacklogs", ignore = true)
    @Mapping(target="password", ignore = true)
    @Mapping(target="role", ignore = true)
    @Mapping(target="email", source = "email" , qualifiedByName = "toLowerCase")
    @Mapping(target="username", source = "username" , qualifiedByName = "toLowerCase")
    void updateUserFromPatch(UserPatchDTO userPatchDTO, @MappingTarget User user);

    @Named("toLowerCase")
    default String toLowerCase(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().toLowerCase();
    }

}
