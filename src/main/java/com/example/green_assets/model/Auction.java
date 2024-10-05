package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
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
    private String pickupLocation;
    private String description;
    @ManyToOne
    private Account winningAccount;
    @ManyToOne(optional = false)
    private MethodOfCollection methodOfCollection;
    @ManyToOne(optional = false)
    private Region region;
    @OneToMany(mappedBy = "auction", cascade = CascadeType.REMOVE)
    private Set<ItemAuction> items;
}
