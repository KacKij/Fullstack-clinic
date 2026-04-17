package com.clinic.backend.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 128)
    private String username;

    @Column(length = 128)
    private String firstname;

    @Column(length = 128)
    private String lastname;

    @Column(unique = true, length = 255)
    private String email;

    @Column(name = "password_hash",  nullable = false)
    @ToString.Exclude
    private String passwordHash;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(length = 255)
    private String occupation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    private Address address;

    @Column(name = "phone_number", length = 64)
    private String phoneNumber;

    @Column(name = "phone_number_ext",  length = 32)
    private String phoneNumberExt;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "updated_by")
    private Long UpdatedBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();
}
