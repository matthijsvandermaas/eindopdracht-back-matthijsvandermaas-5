package com.example.eindopdrachtmatthijsvandermaasback5.Service;


import com.example.eindopdrachtmatthijsvandermaasback5.DTO.UserDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.ProductIdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.UserIdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.Role;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.User;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.RoleRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User u : users) {
            UserDto uDto = userToUserDto(u);
            userDtos.add(uDto);
        }
        return userDtos;
    }


private UserDto userToUserDto(User u) {
    UserDto uDto = new UserDto();
    uDto.setUsername(u.getUsername());
    uDto.setFirstName(u.getFirstName());
    uDto.setLastName(u.getLastName());
    uDto.setCompany(u.getCompany());
    uDto.setEmail(u.getEmail());
    uDto.setPassword(u.getPassword());
    ArrayList<String> roles = new ArrayList<>();
    for (Role role : u.getRoles()) {
        roles.add(role.getRoleName());
    }
    uDto.setRoles(roles.toArray(new String[0]));

    return uDto;
}


    private User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCompany(userDto.getCompany());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        ArrayList<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }
        userDto.setRoles(roles.toArray(new String[0]));
        return user;
    };
public UserDto getUser(String username, String roleName) {
    Optional<User> user = userRepository.findById(username);
    Optional<Role> role = roleRepository.findById(roleName);
    if (user.isPresent()) {
        User u = user.get();
        UserDto uDto = new UserDto();
        userToUserDto(u);
        return (uDto);
    } else {
        throw new UserIdNotFoundException("User not found with name: " + username);
    }
}
    public UserDto createUser(UserDto userDto) {
        User savedUser = userRepository.save(userDtoToUser(userDto));

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRoles(userDto.getRoles());
        return userToUserDto(savedUser);
    }

}

