package com.example.green_assets.modelDTO;

import com.example.green_assets.validators.ValidAccountExist;
import com.example.green_assets.validators.ValidMethodOfCollectionExist;
import com.example.green_assets.validators.ValidRegionExist;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private UUID id;
    @NotNull(message = "Price can't be empty")
    @Positive(message = "Price must be positive number")
    private BigDecimal price;
    private Boolean isAccepted;
    @NotEmpty(message = "Pickup location can't be empty")
    private String pickupLocation;
    private String description;
    @ValidAccountExist
    private UUID clientId;
    @ValidMethodOfCollectionExist
    private String methodOfCollection;
    @ValidRegionExist
    private String region;
}
