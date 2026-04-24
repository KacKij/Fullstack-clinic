package com.clinic.backend.domain.service.patient;


import com.clinic.backend.domain.dto.patient.PatientCreateRequest;
import com.clinic.backend.domain.entity.Address;
import com.clinic.backend.domain.entity.Patient;
import com.clinic.backend.domain.repository.PatientRepository;
import com.clinic.backend.domain.service.address.AddressService;
import com.clinic.backend.domain.util.PeselUtil;
import com.clinic.backend.exception.InvalidPeselException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AddressService addressService;

    @Override
    public Long createPatient(PatientCreateRequest request, Long actorUserId) {

        if (!PeselUtil.isValid(request.pesel())) {
            throw new InvalidPeselException();
        }

        validateDuplicates(request);

        OffsetDateTime now = OffsetDateTime.now();

        Address address = addressService.createAddressSnapshot(request.address(), actorUserId);

        Patient patient = new Patient();

        patient.setFirstname(request.firstName());
        patient.setLastname(request.lastName());
        patient.setPesel(request.pesel());
        patient.setEmail(request.email());
        patient.setPhoneNumber(request.phoneNumber());
        patient.setPhoneNumberExt(request.phoneNumberExt());

        patient.setAddress(address);

        //  PeselUtil methods
        patient.setDateOfBirth(PeselUtil.extractDateOfBirth(request.pesel()));
        patient.setGender(PeselUtil.extractGender(request.pesel()));

        patient.setCreatedAt(now);
        patient.setCreatedBy(actorUserId);

        Patient savedPatient = patientRepository.save(patient);

        return savedPatient.getId();

    }

    private void validateDuplicates(PatientCreateRequest request) {

        if (patientRepository.existsByPesel(request.pesel())) {
            throw new IllegalArgumentException("PESEL already exists");
        }

        if (patientRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("EMAIL already exists");
        }
    }


}
