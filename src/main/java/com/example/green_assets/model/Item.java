package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer weight;
    private String measurements;
    private String pickupLocation;
    private String methodOfCollection;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean modifiable;
    @ManyToOne(optional = false)
    private Region region;
    @ManyToOne(optional = false)
    private Account account;
    @ManyToOne(optional = false)
    private Type type;
    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private Set<Photo> photoSet;
    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private Set<Auction> auctionSet;
    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private Set<Offer> offerSet;
}
