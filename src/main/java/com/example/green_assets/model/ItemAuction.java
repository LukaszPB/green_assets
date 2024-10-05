package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ItemAuction {
    @Embeddable
    class ItemOAuctionId implements Serializable {
        private UUID itemId;
        private UUID auctionId;
    }
    @EmbeddedId
    private ItemOAuctionId id;
    private Integer quantity;
    @ManyToOne(optional = false)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(optional = false)
    @MapsId("auctionId")
    @JoinColumn(name = "auction_id")
    private Auction auction;
}
