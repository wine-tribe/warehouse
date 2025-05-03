package backend.controller;

import backend.dto.OrderDto;
import backend.dto.WarehouseGroupDto;
import backend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Операции с заказами")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить заказ по ID")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получить все заказы")
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить заказ по ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/isDeleted")
    @Operation(summary = "Изменить флаг isDeleted для заказа")
    public ResponseEntity<OrderDto> isDeleted(@PathVariable Long id, @RequestParam boolean isDeleted) {
        return ResponseEntity.ok(orderService.isDeleted(id, isDeleted));
    }

    @PatchMapping("/{id}/{status}")
    @Operation(summary = "Изменить статус заказа")
    public ResponseEntity<OrderDto> changeStatus(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(orderService.changeStatus(id, status));
    }

    @PatchMapping("/{id}/update")
    @Operation(summary = "Обновить детали заказа (notProcess, warehouseId, price)")
    public ResponseEntity<OrderDto> updateOrderDetails(
            @PathVariable Long id,
            @RequestParam(required = false) Boolean notProcess,
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) BigDecimal price) {
        return ResponseEntity.ok(orderService.updateOrderDetails(id, notProcess, warehouseId, price));
    }

    @PostMapping("/save")
    @Operation(summary = "Сохранить новый заказ")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.saveOrder(orderDto));
    }

    @GetMapping("/warehouse/{warehouseId}/orders")
    @Operation(summary = "Получить ID заказов по складу")
    public ResponseEntity<List<Long>> getOrderIdsByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(orderService.getOrderIdsByWarehouse(warehouseId));
    }

    @PatchMapping("/{id}/transfer/{newWarehouseId}")
    @Operation(summary = "Перенести заказ на другой склад")
    public ResponseEntity<OrderDto> transferOrderToAnotherWarehouse(
            @PathVariable Long id,
            @PathVariable Long newWarehouseId) {
        return ResponseEntity.ok(orderService.transferOrderToAnotherWarehouse(id, newWarehouseId));
    }

    @PatchMapping("/transfer")
    @Operation(summary = "Перенести несколько заказов на другой склад")
    public ResponseEntity<List<OrderDto>> transferOrdersToAnotherWarehouse(
            @RequestParam List<Long> orderIds,
            @RequestParam Long newWarehouseId) {
        return ResponseEntity.ok(orderService.transferOrdersToAnotherWarehouse(orderIds, newWarehouseId));
    }

}
