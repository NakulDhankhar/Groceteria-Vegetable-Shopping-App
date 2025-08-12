package com.groceteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groceteria.dto.UserDTO;
import com.groceteria.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST Controller for User management operations.
 * Provides endpoints for user registration, authentication, and profile management.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "APIs for user registration, authentication, and profile management")
public class UserController {
    
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Register a new user.
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.registerUser(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    
    /**
     * Authenticate user login.
     */
    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates user with email and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "401", description = "Invalid credentials"),
        @ApiResponse(responseCode = "403", description = "Account deactivated")
    })
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginRequest loginRequest) {
        UserDTO user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    /**
     * Update user information.
     */
    @PutMapping("/{userId}")
    @Operation(summary = "Update user profile", description = "Updates user information by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<UserDTO> updateUser(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId,
            @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
    /**
     * Get all users.
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves all users in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * Get user by ID.
     */
    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieves user information by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDTO> getUserById(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId) {
        UserDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    /**
     * Get user by email.
     */
    @PostMapping("/forgot-password")
    @Operation(summary = "Get user by email", description = "Retrieves user information by email for password reset")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDTO> getUserByEmail(@RequestBody EmailRequest emailRequest) {
        UserDTO user = userService.getUserByEmail(emailRequest.getEmail());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    /**
     * Delete user by ID.
     */
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Deletes user account by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * Get all vendors.
     */
    @GetMapping("/vendors")
    @Operation(summary = "Get all vendors", description = "Retrieves all users with VENDOR role")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vendors retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    public ResponseEntity<List<UserDTO>> getAllVendors() {
        List<UserDTO> vendors = userService.getAllVendors();
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }
    
    /**
     * Get all regular users.
     */
    @GetMapping("/regular-users")
    @Operation(summary = "Get all regular users", description = "Retrieves all users with USER role")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    public ResponseEntity<List<UserDTO>> getAllRegularUsers() {
        List<UserDTO> users = userService.getAllRegularUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * Toggle user account status.
     */
    @PutMapping("/{userId}/toggle-status")
    @Operation(summary = "Toggle user status", description = "Activates or deactivates user account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User status updated successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDTO> toggleUserStatus(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId) {
        UserDTO user = userService.toggleUserStatus(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    /**
     * Check if email exists.
     */
    @GetMapping("/check-email/{email}")
    @Operation(summary = "Check email existence", description = "Checks if an email address is already registered")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Email check completed")
    })
    public ResponseEntity<Boolean> isEmailExists(
            @Parameter(description = "Email address") @PathVariable("email") String email) {
        boolean exists = userService.isEmailExists(email);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
    
    // Helper classes for request/response
    public static class LoginRequest {
        private String email;
        private String password;
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    public static class EmailRequest {
        private String email;
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
