package backend.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    PENDING("В ожидании"),
    CANCELED("Отменён"),
    RESERVED("Зарезервирован"),
    SOLD("Продан");

    private final String description;
}
