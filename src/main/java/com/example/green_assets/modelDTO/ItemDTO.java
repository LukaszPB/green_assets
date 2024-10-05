package com.example.green_assets.modelDTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal weight;
    private Integer quantity;
    private String measurements;
    private Boolean modifiable;
    private UUID accountId;
    private String type;
}
