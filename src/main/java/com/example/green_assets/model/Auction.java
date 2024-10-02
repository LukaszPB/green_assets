package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auction {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private BigDecimal bid;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean hasEnded;
    @ManyToOne(optional = false)
    private Item item;
    @ManyToOne
    private Account winningAccount;
    @ManyToOne
    private Account seller;
}
