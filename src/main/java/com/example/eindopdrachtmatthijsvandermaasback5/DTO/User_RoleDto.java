package com.example.eindopdrachtmatthijsvandermaasback5.DTO;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.User_Role;
import lombok.Data;

@Data
public class User_RoleDto {
    private String username;
    private String roleName;

    public static User_RoleDto toDto(User_Role user_Role) {
        User_RoleDto user_Roledto = new User_RoleDto();
        user_Roledto.setUsername(user_Role.getUser().getUsername());
        user_Roledto.setRoleName(user_Role.getRole().getRoleName());
        return user_Roledto;
    }

    public static User_Role toEntity(User_RoleDto user_RoleDto) {
        User_Role user_Role = new User_Role();
        user_Role.setUsername(user_RoleDto.getUsername());
        user_Role.setUsername(user_RoleDto.getRoleName());
        return user_Role;
    }
}
