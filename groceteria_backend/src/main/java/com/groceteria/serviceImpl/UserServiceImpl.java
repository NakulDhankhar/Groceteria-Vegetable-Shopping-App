package com.groceteria.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.dto.UserDTO;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.UserRepository;
import com.groceteria.service.UserService;

/**
 * Service implementation for User operations.
 * Provides user management functionality with support for USER and VENDOR roles.
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        // Check if email already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        User user = mapToEntity(userDTO);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setIsActive(true);
        
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }
    
    @Override
    public UserDTO loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password)
            .orElseThrow(() -> new ResourceNotFoundException("User", "email and password", email));
        
        if (!user.getIsActive()) {
            throw new IllegalArgumentException("User account is deactivated");
        }
        
        return mapToDTO(user);
    }
    
    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        
        // Update fields
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setDistrict(userDTO.getDistrict());
        existingUser.setState(userDTO.getState());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setZipcode(userDTO.getZipcode());
        existingUser.setRole(userDTO.getRole());
        existingUser.setUpdatedAt(new Date());
        
        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }
    
    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return mapToDTO(user);
    }
    
    @Override
    public User getUserEntityById(Integer userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }
    
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return mapToDTO(user);
    }
    
    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepository.deleteById(userId);
    }
    
    @Override
    public List<UserDTO> getAllVendors() {
        List<User> vendors = userRepository.findAllVendors();
        return vendors.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<UserDTO> getAllRegularUsers() {
        List<User> users = userRepository.findAllUsers();
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Override
    public List<UserDTO> getUsersByDistrict(String district) {
        List<User> users = userRepository.findByDistrict(district);
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<UserDTO> getActiveUsers() {
        List<User> users = userRepository.findByIsActiveTrue();
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<UserDTO> getUsersByRole(String role) {
        List<User> users = userRepository.findByRoleAndIsActiveTrue(role);
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public UserDTO toggleUserStatus(Integer userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        
        user.setIsActive(!user.getIsActive());
        user.setUpdatedAt(new Date());
        
        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }
    
    // Helper methods for entity-DTO mapping
    private User mapToEntity(UserDTO userDTO) {
        return User.builder()
            .userId(userDTO.getUserId())
            .firstName(userDTO.getFirstName())
            .lastName(userDTO.getLastName())
            .email(userDTO.getEmail())
            .phoneNumber(userDTO.getPhoneNumber())
            .district(userDTO.getDistrict())
            .state(userDTO.getState())
            .address(userDTO.getAddress())
            .zipcode(userDTO.getZipcode())
            .role(userDTO.getRole())
            .build();
    }
    
    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
            .userId(user.getUserId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .district(user.getDistrict())
            .state(user.getState())
            .address(user.getAddress())
            .zipcode(user.getZipcode())
            .role(user.getRole())
            .build();
    }
}
