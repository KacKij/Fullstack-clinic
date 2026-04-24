package com.clinic.backend.domain.service.address;

import com.clinic.backend.domain.dto.address.AddressCreateRequest;
import com.clinic.backend.domain.entity.Address;
import com.clinic.backend.domain.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;


@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address createAddressSnapshot (AddressCreateRequest request, Long actorUserId) {

        if (request == null) {
            return null;
        }

        OffsetDateTime now = OffsetDateTime.now();

        Address address = new Address();

        address.setStreet(request.street());
        address.setStreetNumber(request.streetNumber());
        address.setApartmentNumber(request.apartmentNumber());
        address.setCity(request.city());
        address.setZipCode(request.zipCode());
        address.setState(request.state());
        address.setCountry(request.country());

        address.setCreatedAt(now);
        address.setCreatedBy(actorUserId);

        return addressRepository.save(address);
    }
}
