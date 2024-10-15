package com.example.green_assets.modelDTO;

import com.example.green_assets.validators.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidDatesOrder
public class AuctionDTO {
    private UUID id;
    @NotNull(message = "Bid can't be empty")
    @Positive(message = "Bid must be positive number")
    private BigDecimal bid;
    @NotNull(message = "Start date can't be empty")
    @ValidStartingDate
    private LocalDateTime startDate;
    @NotNull(message = "End date can't be empty")
    private LocalDateTime endDate;
    private Boolean hasEnded;
    @NotEmpty(message = "Pickup location can't be empty")
    private String pickupLocation;
    private String description;
    @ValidAccountExist
    private UUID winningAccountId;
    @ValidMethodOfCollectionExist
    private String methodOfCollection;
    @ValidRegionExist
    private String region;
}

