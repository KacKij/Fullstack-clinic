package com.clinic.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "privileges")
@Data
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false,  length = 128)
    private String name;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();
}
