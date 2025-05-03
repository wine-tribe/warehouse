package backend.service;

import backend.dto.WarehouseGroupDto;
import backend.model.WarehouseGroup;

import java.util.List;

public interface WarehouseGroupService {

    WarehouseGroupDto getById(Long id);
    List<WarehouseGroupDto> getAll();
    void deleteById(Long id);
    WarehouseGroupDto isDeleted(Long id, boolean isDeleted);
    WarehouseGroup findById(Long warehouseId);

}
