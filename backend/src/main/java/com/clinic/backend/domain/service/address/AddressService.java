package com.clinic.backend.domain.service.address;

import com.clinic.backend.domain.dto.address.AddressCreateRequest;
import com.clinic.backend.domain.entity.Address;

public interface AddressService {

    Address createAddressSnapshot (AddressCreateRequest request, Long actorUserId);
}
