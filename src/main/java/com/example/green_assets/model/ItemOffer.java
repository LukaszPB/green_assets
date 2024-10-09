package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemOffer {
    @EmbeddedId
    private ItemOfferId id;
    private Integer quantity;
    @ManyToOne(optional = false)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(optional = false)
    @MapsId("offerId")
    @JoinColumn(name = "offer_id")
    private Offer offer;
}
