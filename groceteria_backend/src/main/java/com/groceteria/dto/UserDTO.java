package com.groceteria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for User data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for User data transfer")
public class UserDTO {
    @Schema(description = "User ID", example = "1001")
    private Integer userId;

    @Schema(description = "First name", example = "John")
    private String firstName;

    @Schema(description = "Last name", example = "Doe")
    private String lastName;

    @Schema(description = "Email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Phone number", example = "+1234567890")
    private String phoneNumber;

    @Schema(description = "District", example = "New York")
    private String district;

    @Schema(description = "State", example = "New York")
    private String state;

    @Schema(description = "Address", example = "123 Main Street, Apt 4B")
    private String address;

    @Schema(description = "Zipcode", example = "10001")
    private String zipcode;

    @Schema(description = "Role", example = "USER", allowableValues = {"USER", "VENDOR"})
    private String role;
} 