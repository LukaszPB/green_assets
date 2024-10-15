package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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
    private String name;
    private String nip;
    private String phoneNumber;
    private String email;
    private String postalCode;
    private String city;
    private String street;
    @ManyToOne(optional = false)
    private Region region;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private Set<Offer> purchaseOffers;
    @OneToMany(mappedBy = "winningAccount", cascade = CascadeType.REMOVE)
    private Set<Auction> winningAuctions;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private Set<Item> items;
    @ManyToMany()
    @JoinTable(
            name = "account_auction",
            joinColumns = @JoinColumn(name ="account_id"),
            inverseJoinColumns = @JoinColumn(name = "auction_id")
    )
    private Set<Auction> observedAuctions;
    @ManyToMany()
    @JoinTable(
            name = "account_contractor",
            joinColumns = @JoinColumn(name ="account_id"),
            inverseJoinColumns = @JoinColumn(name = "contractor_id")
    )
    private List<Account> contractors;
//    @ManyToMany(mappedBy = "contractors")
//    private List<Account> accounts;
}
