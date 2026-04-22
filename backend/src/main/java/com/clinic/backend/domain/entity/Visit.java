package com.clinic.backend.domain.entity;


import com.clinic.backend.domain.enums.VisitStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @ToString.Exclude
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    @ToString.Exclude
    private User doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_schedule_id")
    @ToString.Exclude
    private DoctorShiftSchedule doctorShiftSchedule;

    @Column(name = "slot_start", nullable = false)
    private OffsetDateTime slotStart;

    @Column(name = "slot_end", nullable = false)
    private OffsetDateTime slotEnd;

    @Column(name = "visit_length_in_slots", nullable = false)
    private Integer visitLengthInSlots;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",  nullable = false)
    private VisitStatus status;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
