package backend.mapper;

import backend.dto.WarehouseGroupDto;
import backend.model.WarehouseGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WarehouseGroupMapper {

    private final WarehouseMapper warehouseMapper;

    // Преобразование из Entity в DTO
    public WarehouseGroupDto convertToDto(WarehouseGroup entity) {
        return WarehouseGroupDto.builder()
                .id(entity.getId())
                .externalId(entity.getExternalId())
                .isDeleted(entity.getIsDeleted())
                .code(entity.getCode())
                .name(entity.getName())
                .createdDate(entity.getCreatedDate())
                .createdBy(entity.getCreatedBy())
                .lastModifiedDate(entity.getLastModifiedDate())
                .lastModifiedBy(entity.getLastModifiedBy())
                .warehouses(entity.getWarehouse().stream()
                        .map(warehouseMapper::convertToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    // Преобразование из DTO в Entity
    public WarehouseGroup convertToEntity(WarehouseGroupDto dto) {
        return WarehouseGroup.builder()
                .id(dto.getId())
                .externalId(dto.getExternalId())
                .isDeleted(dto.getIsDeleted())
                .code(dto.getCode())
                .name(dto.getName())
                .createdDate(dto.getCreatedDate())
                .createdBy(dto.getCreatedBy())
                .lastModifiedDate(dto.getLastModifiedDate())
                .lastModifiedBy(dto.getLastModifiedBy())
                .build();
    }
}
