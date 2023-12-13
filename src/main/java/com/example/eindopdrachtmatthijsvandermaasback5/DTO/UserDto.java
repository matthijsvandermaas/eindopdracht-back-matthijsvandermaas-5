package com.example.eindopdrachtmatthijsvandermaasback5.DTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "FirstName cannot be empty")
    private String firstName;
    @NotEmpty(message = "LastName cannot be empty")
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty (message = "Password cannot be empty")
    private String password;


    private String[] roles;

}
