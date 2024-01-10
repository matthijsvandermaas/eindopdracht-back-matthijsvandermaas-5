package com.example.eindopdrachtmatthijsvandermaasback5.Service;


import com.example.eindopdrachtmatthijsvandermaasback5.DTO.RoleDto;
import com.example.eindopdrachtmatthijsvandermaasback5.DTO.UserDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.UserIdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.Role;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.User;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.User_Role;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.RoleRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.UserRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.User_RoleRepository;
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
    private final User_RoleRepository user_RoleRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, User_RoleRepository userRoleRepository, User_RoleRepository user_RoleRepository1) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.user_RoleRepository = user_RoleRepository1;
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


    private UserDto userToUserDto(User user) {
        UserDto uDto = new UserDto();
        uDto.setUsername(user.getUsername());
        uDto.setFirstName(user.getFirstName());
        uDto.setLastName(user.getLastName());
        uDto.setCompany(user.getCompany());
        uDto.setEmail(user.getEmail());
        uDto.setPassword(user.getPassword());
        ArrayList<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
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
    }

    ;

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
    public UserDto getUserByUsername(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            User u = user.get();
            UserDto uDto = userToUserDto(u);
            userToUserDto(u);
            return (uDto);
        } else {
            throw new UserIdNotFoundException("User not found with name: " + username);
        }
    }

    public UserDto createUser(UserDto userDto, RoleDto roleDto) {

        User savedUser = userRepository.save(userDtoToUser(userDto));
        Role savedRole = roleRepository.save(roleDtoToRole(roleDto));

        User_Role userRole = new User_Role();
        userRole.setUser(savedUser);
        userRole.setRole(savedRole);
        user_RoleRepository.save(userRole);

        if (userDto.getRoles() != null) {

            for (String roleName : userDto.getRoles()) {
                Optional<Role> roleOptional = roleRepository.findById("ROLE_" + roleName);
                roleOptional.ifPresent(savedUser::addRole);
            }
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//            userDto.setRoles(userRole.toArray(new String[0]));
        userDto.setRoles(savedUser.getRoles().stream().map(Role::getRoleName).toArray(String[]::new));
        return userToUserDto(savedUser);
    }

    private Role roleDtoToRole(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        return role;
    }


}

