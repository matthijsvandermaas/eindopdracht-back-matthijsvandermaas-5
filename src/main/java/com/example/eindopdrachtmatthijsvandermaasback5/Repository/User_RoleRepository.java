package com.example.eindopdrachtmatthijsvandermaasback5.Repository;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.Role;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_RoleRepository extends JpaRepository<User_Role, String> {
//    List<User_Role> findByUsername(String username);
    List<User_Role> findByRole_RoleName(String roleName);}
