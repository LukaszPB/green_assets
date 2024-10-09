package com.example.green_assets.modelDTO;

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
    private BigDecimal price;
    private Boolean isAccepted;
    private String pickupLocation;
    private String description;
    private UUID clientId;
    private String methodOfCollection;
    private String region;
}
