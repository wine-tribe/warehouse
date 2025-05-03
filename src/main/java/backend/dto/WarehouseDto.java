package backend.dto;

import backend.enumeration.WarehouseStatusEnum;
import backend.model.UserInfo;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDto {
    private Long id;
    private String externalId;
    private Boolean isDeleted;
    private WarehouseStatusEnum status;
    private String warehouseName;
    private Boolean isTransitWarehouse;
    private Long warehouseGroupId;
    private Instant createdDate;
    private UserInfo createdBy;
    private Instant lastModifiedDate;
    private UserInfo lastModifiedBy;
}
