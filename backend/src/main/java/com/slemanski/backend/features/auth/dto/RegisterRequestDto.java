package com.slemanski.backend.features.auth.dto;

import com.slemanski.backend.features.auth.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

    @NotBlank(message="Username is required")
    private String username;

    @NotBlank(message="Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message="Role is required")
    private Role role;
}
