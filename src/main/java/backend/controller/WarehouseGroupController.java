package backend.controller;

import backend.dto.WarehouseGroupDto;
import backend.service.WarehouseGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/warehouse-groups")
@RequiredArgsConstructor
@Tag(name = "Группы складов", description = "Операции с группами складов")
public class WarehouseGroupController {

    private final WarehouseGroupService warehouseGroupService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить группу складов по ID")
    public ResponseEntity<WarehouseGroupDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseGroupService.getById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получить все группы складов")
    public ResponseEntity<List<WarehouseGroupDto>> getAll() {
        return ResponseEntity.ok(warehouseGroupService.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить группу складов по ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        warehouseGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/isDeleted")
    @Operation(summary = "Изменить флаг isDeleted для группы складов")
    public ResponseEntity<WarehouseGroupDto> isDeleted(@PathVariable Long id, @RequestParam boolean isDeleted) {
        return ResponseEntity.ok(warehouseGroupService.isDeleted(id, isDeleted));
    }
}
