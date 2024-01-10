package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;


import com.example.eindopdrachtmatthijsvandermaasback5.DTO.RoleDto;
import com.example.eindopdrachtmatthijsvandermaasback5.DTO.UserDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dDto = userService.getAllUsers();
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }
@PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto, RoleDto roleDto) {
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDto newUser = userService.createUser(userDto, roleDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String username) {
        UserDto userDto = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
//        try {
//            userService.deleteUser(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


}



