package backend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "warehouse")
@ToString(callSuper = true, exclude = "warehouse")
@Accessors(chain = true)
@SuperBuilder
@Table(name = "warehouse_group")
public class WarehouseGroup extends BaseAuditedEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "warehouseGroup", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Warehouse> warehouse = new HashSet<>();
}
