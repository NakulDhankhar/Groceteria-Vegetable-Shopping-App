package com.groceteria.entity;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User entity representing a user in the Groceteria system.
 * This entity stores user information including personal details, contact information,
 * and authentication credentials.
 * 
 * @author Groceteria Team
 * @version 1.0
 * @since 2024
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Schema(description = "User entity representing a user in the Groceteria system")
public class User {
    
    /**
     * Unique identifier for the user.
     * Auto-generated using sequence generator starting from 1000.
     */
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @Column(name = "user_id", nullable = false, updatable = false)
    @Schema(description = "Unique identifier for the user", example = "1001", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer userId;
    
    /**
     * User's first name.
     * Must be between 2 and 50 characters and contain only letters and spaces.
     */
    @Column(name = "first_name", length = 50, nullable = false)
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name can only contain letters and spaces")
    @Schema(description = "User's first name", example = "John", required = true)
    private String firstName;
    
    /**
     * User's last name.
     * Must be between 2 and 50 characters and contain only letters and spaces.
     */
    @Column(name = "last_name", length = 50, nullable = false)
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last name can only contain letters and spaces")
    @Schema(description = "User's last name", example = "Doe", required = true)
    private String lastName;
    
    /**
     * User's date of birth.
     * Optional field for user profile information.
     */
    @Column(name = "date_of_birth")
    @Schema(description = "User's date of birth", example = "1990-01-01", format = "date")
    private Date dateOfBirth;
    
    /**
     * User's gender.
     * Must be one of the predefined values: MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY.
     */
    @Column(name = "gender", length = 20, nullable = false)
    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(MALE|FEMALE|OTHER|PREFER_NOT_TO_SAY)$", 
             message = "Gender must be one of: MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY")
    @Schema(description = "User's gender", example = "MALE", allowableValues = {"MALE", "FEMALE", "OTHER", "PREFER_NOT_TO_SAY"}, required = true)
    private String gender;
    
    /**
     * User's email address.
     * Must be a valid email format and unique across all users.
     */
    @Column(name = "email", unique = true, length = 100, nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Schema(description = "User's email address", example = "john.doe@example.com", required = true)
    private String email;
    
    /**
     * User's password.
     * Must meet security requirements: minimum 8 characters, at least one uppercase,
     * one lowercase, one digit, and one special character.
     */
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
             message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    @Schema(description = "User's password (will be hashed)", example = "SecurePass123!", required = true, writeOnly = true)
    private String password;
    
    /**
     * User's phone number.
     * Must be a valid phone number format.
     */
    @Column(name = "phone_number", length = 15, nullable = false)
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Please provide a valid phone number")
    @Schema(description = "User's phone number", example = "+1234567890", required = true)
    private String phoneNumber;
    
    /**
     * User's district/city.
     * Must be between 2 and 50 characters.
     */
    @Column(name = "district", length = 50, nullable = false)
    @NotBlank(message = "District is required")
    @Size(min = 2, max = 50, message = "District must be between 2 and 50 characters")
    @Schema(description = "User's district or city", example = "New York", required = true)
    private String district;
    
    /**
     * User's state/province.
     * Must be between 2 and 50 characters.
     */
    @Column(name = "state", length = 50, nullable = false)
    @NotBlank(message = "State is required")
    @Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
    @Schema(description = "User's state or province", example = "New York", required = true)
    private String state;
    
    /**
     * User's complete address.
     * Must be between 10 and 200 characters.
     */
    @Column(name = "address", length = 200, nullable = false)
    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 200, message = "Address must be between 10 and 200 characters")
    @Schema(description = "User's complete address", example = "123 Main Street, Apt 4B", required = true)
    private String address;
    
    /**
     * User's zip/postal code.
     * Must be between 5 and 10 characters.
     */
    @Column(name = "zipcode", length = 10, nullable = false)
    @NotBlank(message = "Zipcode is required")
    @Size(min = 5, max = 10, message = "Zipcode must be between 5 and 10 characters")
    @Pattern(regexp = "^[0-9A-Za-z\\s-]+$", message = "Zipcode can only contain letters, numbers, spaces, and hyphens")
    @Schema(description = "User's zip or postal code", example = "10001", required = true)
    private String zipcode;
    
    /**
     * User's role in the system.
     * Must be one of the predefined roles: USER, VENDOR.
     */
    @Column(name = "role", length = 20, nullable = false)
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^(USER|VENDOR)$", 
             message = "Role must be one of: USER, VENDOR")
    @Schema(description = "User's role in the system", example = "USER", 
            allowableValues = {"USER", "VENDOR"}, required = true)
    private String role;
    
    /**
     * Timestamp when the user was created.
     * Automatically set when the entity is persisted.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Timestamp when the user was created", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createdAt;
    
    /**
     * Timestamp when the user was last updated.
     * Automatically updated when the entity is modified.
     */
    @Column(name = "updated_at")
    @Schema(description = "Timestamp when the user was last updated", accessMode = Schema.AccessMode.READ_ONLY)
    private Date updatedAt;
    
    /**
     * Flag indicating if the user account is active.
     * Defaults to true for new users.
     */
    @Column(name = "is_active", nullable = false)
    @Schema(description = "Flag indicating if the user account is active", example = "true", defaultValue = "true")
    private Boolean isActive = true;
    

    

    

    
}
