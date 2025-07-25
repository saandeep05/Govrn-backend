package com.saandeep.govrn.dto;

import com.saandeep.govrn.util.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    @NotBlank(message = "Aadhaar number is required")
    @Size(min = 12, max = 12, message = "12 digits required")
    private String aadhaarNumber;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private UserRole role;

    @NotNull(message = "Region id is required")
    private Long regionId;
}
