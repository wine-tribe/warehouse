package backend.repository;

import backend.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("SELECT COUNT(o) > 0 FROM Order o WHERE o.warehouse.id = :warehouseId")
    boolean hasOrders(Long warehouseId);

    @Query("SELECT w.externalId FROM Warehouse w ORDER BY w.id DESC LIMIT 1")
    Optional<String> findLastExternalId();

    Optional<Warehouse> findByWarehouseName(String warehouseName);
}
