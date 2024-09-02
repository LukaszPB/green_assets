package com.example.green_assets.modelDTO;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private UUID id;
    private String name;
    private String description;
    private Integer weight;
    private String measurements;
    private String pickupLocation;
    private String methodOfCollection;
    private String region;
    private Long accountId;
    private String type;
}
