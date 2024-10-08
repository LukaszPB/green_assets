package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
@Entity
@Getter
@Setter
public class MethodOfCollection {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "methodOfCollection", cascade = CascadeType.REMOVE)
    private Set<Offer> offers;
    @OneToMany(mappedBy = "methodOfCollection", cascade = CascadeType.REMOVE)
    private Set<Auction> auctions;
}
