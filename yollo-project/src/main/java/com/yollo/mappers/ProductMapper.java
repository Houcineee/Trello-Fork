package com.yollo.mappers;

import com.yollo.dtos.ProductPatchDTO;
import com.yollo.dtos.ProductRequestDTO;
import com.yollo.dtos.ProductResponseDTO;
import com.yollo.models.ProductBacklog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "epic", ignore = true)
    @Mapping(target = "sprintBacklogs", ignore = true)
    @Mapping(target = "members" , ignore = true)
    ProductBacklog toEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toDTO(ProductBacklog productBacklog);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "epic", ignore = true)
    @Mapping(target = "sprintBacklogs", ignore = true)
    @Mapping(target = "members" , ignore = true)
    void updateProductFromPatch(ProductPatchDTO patch, @MappingTarget ProductBacklog entity);
}
