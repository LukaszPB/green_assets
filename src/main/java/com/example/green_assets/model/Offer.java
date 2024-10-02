package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private BigDecimal price;
    private Boolean isAccepted;
    @ManyToOne(optional = false)
    private Account client;
    @ManyToOne(optional = false)
    private Account seller;
    @ManyToMany(mappedBy = "offers")
    private Set<Item> items;
}
