package com.example.green_assets.modelDTO;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private UUID id;
    private String name;
    private String nip;
    private String phoneNumber;
    private String email;
    private String postalCode;
    private String city;
    private String street;
    private String region;
    private String role;
}
