package backend.service;

import backend.dto.WarehouseDto;
import backend.model.Warehouse;

import java.util.List;

public interface WarehouseService {
    WarehouseDto getById(Long id);
    List<WarehouseDto> getAll();
    void deleteById(Long id);
    WarehouseDto isDeleted(Long id, boolean isDeleted);
    WarehouseDto changeStatus(Long id, String status);
    Warehouse findById(Long warehouseId);
    WarehouseDto saveWarehouse(WarehouseDto warehouseDto);
}
