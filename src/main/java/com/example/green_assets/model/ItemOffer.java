package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ItemOffer {
    @Embeddable
    class ItemOfferId implements Serializable {
        private UUID itemId;
        private UUID offerId;
    }
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
