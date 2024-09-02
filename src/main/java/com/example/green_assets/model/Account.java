package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String nip;
    private String phoneNumber;

    @ManyToOne(optional = false)
    private Role role;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private Set<Offer> offerSet;
    @OneToMany(mappedBy = "winningAccount", cascade = CascadeType.REMOVE)
    private Set<Auction> auctionSet;
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<Item> itemSet;
}
