package com.example.green_assets.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String type;
    private String name;
    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] data;
    @ManyToOne(optional = false)
    private Item item;
}
