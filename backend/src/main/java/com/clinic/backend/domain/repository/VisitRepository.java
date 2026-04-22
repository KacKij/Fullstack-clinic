package com.clinic.backend.domain.repository;

import com.clinic.backend.domain.entity.Visit;
import com.clinic.backend.domain.enums.VisitStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findAllByOrderBySlotStartAsc();

    List<Visit> findByDoctor_IdAndSlotStartBetweenOrderBySlotStartAsc(
            Long doctorId,
            OffsetDateTime from,
            OffsetDateTime to
    );

    List<Visit> findByDoctor_IdAndStatusAndSlotStartBetweenOrderBySlotStartAsc(
            Long doctorId,
            VisitStatus status,
            OffsetDateTime from,
            OffsetDateTime to
    );

    List<Visit> findByDoctorShiftSchedule_IdOrderBySlotStartAsc(Long shiftScheduleId);

    List<Visit> findByPatient_IdOrderBySlotStartDesc(Long patientId);

    List<Visit> findByPatient_IdAndStatusOrderBySlotStartDesc(
            Long patientId,
            VisitStatus status
    );

    List<Visit> findByStatusOrderBySlotStartAsc(VisitStatus status);

    Page<Visit> findByStatus(VisitStatus status, Pageable pageable);

    @Query("""
        SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END
        FROM Visit v
        WHERE v.doctor.id = :doctorId
          AND v.status <> com.clinic.backend.domain.enums.VisitStatus.CANCELLED
          AND v.slotStart < :slotEnd
          AND v.slotEnd > :slotStart
    """)
    boolean existsOverlappingVisit(
            @Param("doctorId") Long doctorId,
            @Param("slotStart") OffsetDateTime slotStart,
            @Param("slotEnd") OffsetDateTime slotEnd
    );

    @Query("""
        SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END
        FROM Visit v
        WHERE v.id <> :visitId
          AND v.doctor.id = :doctorId
          AND v.status <> com.clinic.backend.domain.enums.VisitStatus.CANCELLED
          AND v.slotStart < :slotEnd
          AND v.slotEnd > :slotStart
    """)
    boolean existsOverlappingVisitExcludingCurrent(
            @Param("visitId") Long visitId,
            @Param("doctorId") Long doctorId,
            @Param("slotStart") OffsetDateTime slotStart,
            @Param("slotEnd") OffsetDateTime slotEnd
    );

    @Query("""
        SELECT v
        FROM Visit v
        WHERE v.slotStart >= :from
          AND v.slotStart < :to
        ORDER BY v.slotStart ASC
    """)
    List<Visit> findVisitsBetween(
            @Param("from") OffsetDateTime from,
            @Param("to") OffsetDateTime to
    );

    @Query("""
        SELECT v
        FROM Visit v
        WHERE v.doctor.id = :doctorId
          AND v.slotStart >= :from
          AND v.slotStart < :to
        ORDER BY v.slotStart ASC
    """)
    List<Visit> findDoctorVisitsBetween(
            @Param("doctorId") Long doctorId,
            @Param("from") OffsetDateTime from,
            @Param("to") OffsetDateTime to
    );

    Long countByStatus(VisitStatus status);

    Long countByDoctor_IdAndStatus(Long doctorId, VisitStatus status);

    Optional<Visit> findFirstByPatient_IdAndStatusAndSlotStartAfterOrderBySlotStartAsc(
            Long patientId,
            VisitStatus status,
            OffsetDateTime now
    );

}
