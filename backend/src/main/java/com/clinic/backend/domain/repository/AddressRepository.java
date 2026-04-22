package com.clinic.backend.domain.repository;

import com.clinic.backend.domain.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByOrderByCityAscStreetAscStreetNumberAsc();

    Page<Address> findByCityContainingIgnoreCase(
            String city,
            Pageable pageable
    );

    Page<Address> findByStateContainingIgnoreCase(
            String state,
            Pageable pageable
    );

    List<Address> findByZipcode(String zipcode);

    List<Address> findByCountryIgnoreCase(String country);

    Optional<Address> findByStreetAndStreetNumberAndApartmentNumberAndCityAndZipcodeAndStateAndCountry(
            String street,
            String streetNumber,
            String apartmentNumber,
            String city,
            String zipcode,
            String state,
            String country
    );

    long countByCityIgnoreCase(String city);

    List<Address> findByCreatedAtAfterOrderByCreatedAtDesc(
            OffsetDateTime createdAt
    );

}
