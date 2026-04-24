package com.clinic.backend.domain.service.patient;

import com.clinic.backend.domain.dto.patient.PatientCreateRequest;

public interface PatientService {

    Long createPatient(PatientCreateRequest request, Long actorUserId);
}
