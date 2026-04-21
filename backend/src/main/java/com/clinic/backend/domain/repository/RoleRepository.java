package com.clinic.backend.domain.repository;

import com.clinic.backend.domain.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);

    List<Role> findAllByOrderByNameAsc();

    Set<Role> findByNameIn(Collection<String> names);

    @Query("""
    SELECT DISTINCT r
    FROM Role r
    LEFT JOIN FETCH r.privileges
    WHERE r.name = :name
""")
    Optional<Role> findByNameWithPrivileges(@Param("name")String name);
}
