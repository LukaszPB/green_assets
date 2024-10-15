package com.example.green_assets.modelDTO;

import com.example.green_assets.validators.ValidRegionExist;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private UUID id;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "NIP can't be empty")
    private String nip;
    @NotEmpty(message = "Phone number can't be empty")
    private String phoneNumber;
    @NotEmpty(message = "Email can't be empty")
    private String email;
    @NotEmpty(message = "Postal code can't be empty")
    private String postalCode;
    @NotEmpty(message = "City can't be empty")
    private String city;
    @NotEmpty(message = "Street can't be empty")
    private String street;
    @ValidRegionExist
    private String region;
}
