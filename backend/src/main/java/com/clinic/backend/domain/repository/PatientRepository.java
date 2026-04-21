package com.clinic.backend.domain.repository;

import com.clinic.backend.domain.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPesel(String pesel);

    Optional<Patient> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPesel(String pesel);

    List<Patient> findAllByOrderByLastnameAscFirstnameAsc();

    Page<Patient> findByLastnameContainingIgnoreCase(String lastname, Pageable pageable);

    Page<Patient> findByFirstnameContainingIgnoreCase(String firstname, Pageable pageable);

    Page<Patient> findByPhoneNumberContaining(String phoneNumber, Pageable pageable);

    Page<Patient> findByEmailContaining(String email, Pageable pageable);

    @Query("""
        SELECT p
        FROM Patient p
        WHERE LOWER(p.firstname) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(p.lastname) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(p.email) LIKE LOWER(CONCAT('%', :search, '%'))
           OR p.phoneNumber LIKE CONCAT('%', :search, '%')
    """)
    Page<Patient> searchPatients(@Param("search") String search, Pageable pageable);

    List<Patient> findByLastVisitDoctor_IdOrderByLastVisitDateDesc(Long doctorId);

    List<Patient> findByLastVisitDate(LocalDate date);

    List<Patient> findByLastVisitDateBetween(LocalDate from, LocalDate to);

     @Query("""
        SELECT p
        FROM Patient p
        WHERE p.lastVisitDate IS NULL
        ORDER BY p.lastname ASC, p.firstname ASC
    """)
    List<Patient> findPatientsNeverVisited();

    @Query("""
        SELECT p
        FROM Patient p
        WHERE p.pesel = :pesel
    """)
    Optional<Patient> findSecureByPesel(@Param("pesel") String pesel);
}
