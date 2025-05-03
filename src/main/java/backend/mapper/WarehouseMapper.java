package backend.mapper;

import backend.dto.CargoDto;
import backend.dto.WarehouseDto;
import backend.exception.ResourceNotFoundException;
import backend.model.Warehouse;
import backend.model.WarehouseGroup;
import backend.repository.WarehouseGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WarehouseMapper {

    private final WarehouseGroupRepository warehouseGroupRepository;

    // Преобразование из Entity в DTO
    public WarehouseDto convertToDto(Warehouse entity) {
        return WarehouseDto.builder()
                .id(entity.getId())
                .externalId(entity.getExternalId())
                .isDeleted(entity.getIsDeleted())
                .status(entity.getStatus())
                .warehouseName(entity.getWarehouseName())
                .isTransitWarehouse(entity.isTransitWarehouse())
                .warehouseGroupId(entity.getWarehouseGroup() != null ? entity.getWarehouseGroup().getId() : null)
                .createdDate(entity.getCreatedDate())
                .createdBy(entity.getCreatedBy())
                .lastModifiedDate(entity.getLastModifiedDate())
                .lastModifiedBy(entity.getLastModifiedBy())
                .build();
    }

    // Преобразование из DTO в Entity
    public Warehouse convertToEntity(WarehouseDto dto) {

        WarehouseGroup warehouseGroup = warehouseGroupRepository.findById(dto.getWarehouseGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("WarehouseGroup not found with id: " + dto.getWarehouseGroupId()));

        return Warehouse.builder()
                .id(dto.getId())
                .externalId(dto.getExternalId())
                .isDeleted(dto.getIsDeleted())
                .status(dto.getStatus())
                .warehouseName(dto.getWarehouseName())
                .isTransitWarehouse(dto.getIsTransitWarehouse())
                .warehouseGroup(warehouseGroup)
                .createdDate(dto.getCreatedDate())
                .createdBy(dto.getCreatedBy())
                .lastModifiedDate(dto.getLastModifiedDate())
                .lastModifiedBy(dto.getLastModifiedBy())
                .build();
    }

}
