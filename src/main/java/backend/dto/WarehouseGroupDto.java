package backend.dto;

import backend.model.UserInfo;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseGroupDto {
    private Long id;
    private String externalId;
    private Boolean isDeleted;
    private String code;
    private String name;
    private Instant createdDate;
    private UserInfo createdBy;
    private Instant lastModifiedDate;
    private UserInfo lastModifiedBy;
    private Set<WarehouseDto> warehouses;
}
