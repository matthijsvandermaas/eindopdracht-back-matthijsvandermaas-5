package com.example.eindopdrachtmatthijsvandermaasback5.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProfileDto {
    private Long id;
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "This needs to be an email address")
    private String email;
    @Email(message = "This needs to be an company name")
    private String company;

}
