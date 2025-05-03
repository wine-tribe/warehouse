package backend.model;

import backend.enumeration.WarehouseStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
@SuperBuilder
@Table(name = "warehouse")
public class Warehouse extends BaseAuditedEntity{

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WarehouseStatusEnum status;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "is_transit_warehouse")
    private boolean isTransitWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_GROUP_ID", referencedColumnName = "ID")
    private WarehouseGroup warehouseGroup;
}
