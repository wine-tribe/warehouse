package backend.mapper;

import backend.dto.CargoDto;
import backend.dto.OrderDto;
import backend.dto.WarehouseDto;
import backend.enumeration.EntityType;
import backend.enumeration.OrderStatusEnum;
import backend.exception.ResourceNotFoundException;
import backend.model.Order;
import backend.model.Warehouse;
import backend.repository.WarehouseRepository;
import backend.utils.ExternalIdGenerator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final WarehouseRepository warehouseRepository;
    private final ExternalIdGenerator externalIdGenerator;

    // Преобразование из Entity в DTO
    public OrderDto convertToDto(Order entity) {
        return OrderDto.builder()
                .id(entity.getId())
                .externalId(entity.getExternalId())
                .isDeleted(entity.getIsDeleted())
                .orderName(entity.getName())
                .notProcess(entity.getNotProcess())
                .status(entity.getStatus())
                .price(entity.getPrice())
                .warehouseId(entity.getWarehouse() != null ? entity.getWarehouse().getId() : null)
                .createdDate(entity.getCreatedDate())
                .createdBy(entity.getCreatedBy())
                .lastModifiedDate(entity.getLastModifiedDate())
                .lastModifiedBy(entity.getLastModifiedBy())
                .build();
    }

    public List<Order> convertAllToEntities(List<OrderDto> dtos) {
        // Кэш по ID склада
        Map<Long, Warehouse> warehouseCache = new HashMap<>();

        return dtos.stream()
                .map(dto -> {
                    Warehouse warehouse = warehouseCache.computeIfAbsent(dto.getWarehouseId(), id ->
                            warehouseRepository.findById(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id: " + id)));

                    return Order.builder()
                            .id(dto.getId())
                            .externalId(dto.getExternalId())
                            .isDeleted(dto.getIsDeleted())
                            .name(dto.getOrderName())
                            .notProcess(dto.getNotProcess())
                            .status(dto.getStatus())
                            .price(dto.getPrice())
                            .warehouse(warehouse)
                            .createdDate(dto.getCreatedDate())
                            .createdBy(dto.getCreatedBy())
                            .lastModifiedDate(dto.getLastModifiedDate())
                            .lastModifiedBy(dto.getLastModifiedBy())
                            .build();
                })
                .collect(Collectors.toList());
    }


    public List<OrderDto> mapAllWithGeneratedIds(List<CargoDto> cargos) {
        List<String> externalIds = externalIdGenerator.generateNextExternalIds(EntityType.ORDER, cargos.size());

        List<OrderDto> orders = new ArrayList<>();

        for (int i = 0; i < cargos.size(); i++) {
            CargoDto cargo = cargos.get(i);
            String externalId = externalIds.get(i);

            Warehouse warehouse = warehouseRepository.findByWarehouseName(cargo.getWarehouseName())
                    .orElseThrow(() -> new EntityNotFoundException("Склад не найден: " + cargo.getWarehouseName()));

            OrderDto order = OrderDto.builder()
                    .externalId(externalId)
                    .isDeleted(cargo.getIsDeleted())
                    .orderName(cargo.getName())
                    .notProcess(Boolean.FALSE)
                    .status(OrderStatusEnum.PENDING)
                    .price(cargo.getPrice())
                    .warehouseId(warehouse.getId())
                    .createdDate(cargo.getCreatedDate())
                    .createdBy(cargo.getCreatedBy())
                    .lastModifiedDate(cargo.getLastModifiedDate())
                    .lastModifiedBy(cargo.getLastModifiedBy())
                    .build();

            orders.add(order);
        }

        return orders;
    }

//    public OrderDto fromCargo(CargoDto cargo) {
//
//        Warehouse warehouse = warehouseRepository.findByWarehouseName(cargo.getWarehouseName())
//                .orElseThrow(() -> new EntityNotFoundException("Склад не найден: " + cargo.getWarehouseName()));
//
//        String newExternalId = externalIdGenerator.generateNextExternalId(EntityType.ORDER);
//
//
//        return OrderDto.builder()
//                .externalId(newExternalId)
//                .isDeleted(cargo.getIsDeleted())
//                .orderName(cargo.getName())
//                .notProcess(Boolean.FALSE)
//                .status(OrderStatusEnum.PENDING)
//                .price(cargo.getPrice())
//                .warehouseId(warehouse.getId())
//                .createdDate(cargo.getCreatedDate())
//                .createdBy(cargo.getCreatedBy())
//                .lastModifiedDate(cargo.getLastModifiedDate())
//                .lastModifiedBy(cargo.getLastModifiedBy())
//                .build();
//    }
}
