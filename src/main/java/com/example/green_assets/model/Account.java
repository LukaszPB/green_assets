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
public class Account {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String nip;
    private String phoneNumber;
    @ManyToOne(optional = false)
    private Role role;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private Set<Offer> purchaseOffers;
    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private Set<Offer> sellerOffers;
    @OneToMany(mappedBy = "winningAccount", cascade = CascadeType.REMOVE)
    private Set<Auction> auctionSet;
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<Item> itemSet;
}
