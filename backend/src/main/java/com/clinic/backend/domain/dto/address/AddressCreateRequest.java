package com.clinic.backend.domain.dto.address;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record AddressCreateRequest (

    @NotBlank
    @Size(max = 256)
    String street,

    @NotBlank
    @Size(max = 64)
    String streetNumber,

    @NotBlank
    @Size(max = 64)
    String apartmentNumber,

    @NotBlank
    @Size(max = 256)
    String city,

    @NotBlank
    @Size(max = 32)
    String zipCode,

    @NotBlank
    @Size(max = 256)
    String state,

    @NotBlank
    @Size(max = 256)
    String country
) {}
