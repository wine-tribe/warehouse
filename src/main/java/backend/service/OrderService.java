package backend.service;

import backend.dto.CargoDto;
import backend.dto.OrderDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    OrderDto getById(Long id);
    List<OrderDto> getAll();
    void deleteById(Long id);
    OrderDto isDeleted(Long id, boolean isDeleted);
    OrderDto changeStatus(Long id, String status);
    OrderDto updateOrderDetails(Long id, Boolean notProcess, Long warehouseId, BigDecimal price);
    OrderDto saveOrder(OrderDto orderDto);
    List<Long> getOrderIdsByWarehouse(Long warehouseId);
    OrderDto transferOrderToAnotherWarehouse(Long id, Long newWarehouseId);
    List<OrderDto> transferOrdersToAnotherWarehouse(List<Long> orderIds, Long newWarehouseId);
    void saveFromCargos(List<CargoDto> cargos);

}
