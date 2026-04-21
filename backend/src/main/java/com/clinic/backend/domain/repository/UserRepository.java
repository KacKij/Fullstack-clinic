package com.clinic.backend.domain.repository;

import com.clinic.backend.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("""
        SELECT DISTINCT u
        FROM User u
        LEFT JOIN FETCH u.roles r
        WHERE u.username = :username
    """)
    Optional<User> findByUsernameWithRoles(@Param("username") String username);

    @Query("""
        SELECT DISTINCT u
        FROM User u
        LEFT JOIN FETCH u.roles r
        LEFT JOIN FETCH r.privileges
        WHERE u.username = :username
    """)
    Optional<User> findByUsernameWithRolesAndPrivileges(@Param("username") String username);

    List<User> findAllByEnabledTrue();

    List<User> findAllByOrderByLastnameAscFirstnameAsc();

    List<User> findByRoles_Name(String roleName);

    Page<User> findByLastnameContainingIgnoreCase(String lastname, Pageable pageable);

    Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    @Query("""
        SELECT u
        FROM User u
        WHERE LOWER(u.firstname) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    Page<User> searchUsers(@Param("search") String search, Pageable pageable);

    @Query("""
        SELECT DISTINCT u
        FROM User u
        JOIN u.roles r
        WHERE r.name = 'DOCTOR'
          AND u.enabled = true
        ORDER BY u.lastname ASC, u.firstname ASC
    """)
    List<User> findActiveDoctors();

}
