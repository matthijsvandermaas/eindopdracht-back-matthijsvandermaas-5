package com.example.eindopdrachtmatthijsvandermaasback5.DTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthDTO {
    @NotEmpty (message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Confirm password cannot be empty")
    private String password;
}
