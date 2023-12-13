package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;


import com.example.eindopdrachtmatthijsvandermaasback5.DTO.ProfileAndUserDto;
import com.example.eindopdrachtmatthijsvandermaasback5.DTO.UserDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dDto = userService.getAllUsers();
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUserWithProfile(@Valid @RequestBody ProfileAndUserDto userDto) {
        UserDto result = userService.createUserWithProfile(userDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}



