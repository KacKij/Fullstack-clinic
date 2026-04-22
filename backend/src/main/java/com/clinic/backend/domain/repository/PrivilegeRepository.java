package com.clinic.backend.domain.repository;

import com.clinic.backend.domain.entity.Privilege;
import com.clinic.backend.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name);

    boolean existsByName(String name);

    List<Privilege> findAllByOrderByNameAsc();

    Set<Privilege> findByNameIn(Collection<String> names);

    List<Privilege> findByRoles_NameOrderByNameAsc(String roleName);

    Long countByRoles_Name(String roleName);

    Page<Privilege> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );


}
