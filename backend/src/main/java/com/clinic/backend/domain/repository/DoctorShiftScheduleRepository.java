package com.clinic.backend.domain.repository;

import com.clinic.backend.domain.entity.DoctorShiftSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DoctorShiftScheduleRepository extends JpaRepository<DoctorShiftSchedule, Long> {

    List<DoctorShiftSchedule> findAllByOrderByDateAscStartTimeAsc();

    List<DoctorShiftSchedule> findByDoctor_IdAndDateOrderByDateAsc(
            Long doctorId,
            LocalDate date
    );

    List<DoctorShiftSchedule> findByDoctor_IdAndDateBetweenOrderByDateAscStartTimeAsc(
            Long doctorId,
            LocalDate from,
            LocalDate to
    );

    List<DoctorShiftSchedule> findByDateOrderByStartTimeAsc(LocalDate date);

    List<DoctorShiftSchedule> findByDateBetweenOrderByDateAscStartTimeAsc(
            LocalDate from,
            LocalDate to
    );

    Page<DoctorShiftSchedule> findByDoctor_Id(
            Long doctorId,
            Pageable pageable
    );

    Page<DoctorShiftSchedule> findByDateBetween(
            LocalDate from,
            LocalDate to,
            Pageable pageable
    );

    @Query("""
        SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
        FROM DoctorShiftSchedule s
        WHERE s.doctor.id = :doctorId
          AND s.date = :date
          AND s.startTime < :endTime
          AND s.endTime > :startTime
    """)
    boolean existsOverlappingShift(
            @Param("doctorId") Long doctorId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    @Query("""
        SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
        FROM DoctorShiftSchedule s
        WHERE s.id <> :scheduleId
          AND s.doctor.id = :doctorId
          AND s.date = :date
          AND s.startTime < :endTime
          AND s.endTime > :startTime
    """)
    boolean existsOverlappingShiftExcludingCurrent(
            @Param("scheduleId") Long scheduleId,
            @Param("doctorId") Long doctorId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    @Query("""
        SELECT s
        FROM DoctorShiftSchedule s
        WHERE s.doctor.id = :doctorId
          AND s.date >= :today
        ORDER BY s.date ASC, s.startTime ASC
    """)
    List<DoctorShiftSchedule> findUpcomingSchedules(
            @Param("doctorId") Long doctorId,
            @Param("today") LocalDate today
    );

    Optional<DoctorShiftSchedule>
    findFirstByDoctor_IdAndDateGreaterThanEqualOrderByDateAscStartTimeAsc(
            Long doctorId,
            LocalDate today
    );



}
