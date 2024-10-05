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
public class Item {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private BigDecimal weight;
    private Integer quantity;
    private String measurements;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean modifiable;
    @ManyToOne(optional = false)
    private Account owner;
    @ManyToOne(optional = false)
    private Type type;
    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private Set<Photo> photos;
    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private Set<ItemOffer> offers;
    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private Set<ItemAuction> auctions;
}
