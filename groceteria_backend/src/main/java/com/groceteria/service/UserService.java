package com.groceteria.service;

import java.util.List;

import com.groceteria.dto.UserDTO;
import com.groceteria.entity.User;

/**
 * Service interface for User operations.
 * Provides user management functionality with support for USER and VENDOR roles.
 */
public interface UserService {
    
    /**
     * Register a new user.
     */
    UserDTO registerUser(UserDTO userDTO);
    
    /**
     * Authenticate user login.
     */
    UserDTO loginUser(String email, String password);
    
    /**
     * Update user information.
     */
    UserDTO updateUser(UserDTO userDTO, Integer userId);
    
    /**
     * Get user by ID.
     */
    UserDTO getUserById(Integer userId);
    
    /**
     * Get user entity by ID (for internal use).
     */
    User getUserEntityById(Integer userId);
    
    /**
     * Get all users.
     */
    List<UserDTO> getAllUsers();
    
    /**
     * Get user by email.
     */
    UserDTO getUserByEmail(String email);
    
    /**
     * Delete user by ID.
     */
    void deleteUser(Integer userId);
    
    /**
     * Get all vendors (users with VENDOR role).
     */
    List<UserDTO> getAllVendors();
    
    /**
     * Get all regular users (users with USER role).
     */
    List<UserDTO> getAllRegularUsers();
    
    /**
     * Check if email exists.
     */
    boolean isEmailExists(String email);
    
    /**
     * Get users by district.
     */
    List<UserDTO> getUsersByDistrict(String district);
    
    /**
     * Get active users only.
     */
    List<UserDTO> getActiveUsers();
    
    /**
     * Get users by role.
     */
    List<UserDTO> getUsersByRole(String role);
    
    /**
     * Activate/deactivate user account.
     */
    UserDTO toggleUserStatus(Integer userId);
}
