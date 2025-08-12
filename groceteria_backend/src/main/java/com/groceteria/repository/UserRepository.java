package com.groceteria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.groceteria.entity.User;

/**
 * Repository interface for User entity operations.
 * Provides methods for user management with support for USER and VENDOR roles.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    /**
     * Find user by email and password for authentication.
     */
    Optional<User> findByEmailAndPassword(String email, String password);

    /**
     * Find user by email address.
     */
    Optional<User> findByEmail(String email);

    /**
     * Find all users by role (USER or VENDOR).
     */
    List<User> findByRole(String role);

    /**
     * Find all vendors (users with VENDOR role).
     */
    @Query("SELECT u FROM User u WHERE u.role = 'VENDOR'")
    List<User> findAllVendors();

    /**
     * Find all regular users (users with USER role).
     */
    @Query("SELECT u FROM User u WHERE u.role = 'USER'")
    List<User> findAllUsers();

    /**
     * Check if email exists in the system.
     */
    boolean existsByEmail(String email);

    /**
     * Find users by district for location-based queries.
     */
    List<User> findByDistrict(String district);

    /**
     * Find active users only.
     */
    List<User> findByIsActiveTrue();

    /**
     * Find users by role and active status.
     */
    List<User> findByRoleAndIsActiveTrue(String role);
}
