package com.example.green_assets.modelDTO;

import com.example.green_assets.validators.ValidAccountExist;
import com.example.green_assets.validators.ValidTypeExist;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @Size(max = 1000)
    private String description;
    @NotNull(message = "Weight can't be empty")
    @Positive(message = "Weight must be positive number")
    private BigDecimal weight;
    @NotNull(message = "Quantity can't be empty")
    @Positive(message = "Quantity must be positive number")
    private Integer quantity;
    @NotEmpty(message = "Measurements can't be empty")
    @Size(max = 100)
    private String measurements;
    private Boolean modifiable;
    @ValidAccountExist
    private UUID accountId;
    @ValidTypeExist
    private String type;
}
