package com.example.green_assets.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
class ItemOfferId implements Serializable {
    private UUID itemId;
    private UUID offerId;
    public ItemOfferId() {}
    public ItemOfferId(UUID itemId, UUID offerId) {
        this.itemId = itemId;
        this.offerId = offerId;
    }
}
