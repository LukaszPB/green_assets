package com.example.green_assets.modelDTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDTO {
    private UUID id;
    private BigDecimal bid;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean hasEnded;
    private String pickupLocation;
    private String description;
    private UUID winningAccountId;
    private String methodOfCollection;
    private String region;
}

