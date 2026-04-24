package com.clinic.backend.domain.dto.patient;


import com.clinic.backend.domain.dto.address.AddressCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatientCreateRequest (

    @NotBlank
    @Size(max = 128)
    String firstName,

    @NotBlank
    @Size(max = 128)
    String lastName,

    @NotBlank
    @Size(max = 11, min = 11)
    String pesel,

    @NotBlank
    @Size(max = 256)
    String email,

    @Size(max = 64)
    String phoneNumber,

    @Size(max = 32)
    String phoneNumberExt,

    @Valid
    AddressCreateRequest address
) {}
