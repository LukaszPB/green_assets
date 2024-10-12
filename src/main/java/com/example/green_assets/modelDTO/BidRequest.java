package com.example.green_assets.modelDTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BidRequest {
    private BigDecimal amount;
}
