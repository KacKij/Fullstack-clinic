package com.clinic.backend.domain.entity;


import com.clinic.backend.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String firstname;

    @Column(nullable = false, length = 128)
    private String lastname;

    @Column(nullable = false, unique = true, length = 11)
    private String pesel;

    @Column(nullable = false, unique = true, length = 256)
    private String email;

    @Column(name = "phone_number", length = 64)
    private String phoneNumber;

    @Column(name = "phone_number_ext",  length = 32)
    private String phoneNumberExt;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    private Address address;

    @Column(name = "last_visit_date")
    private LocalDate lastVisitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_visit_doctor_id")
    @ToString.Exclude
    private User lastVisitDoctor;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "created_by")
    private Long createdBy;
}
