package com.example.eindopdrachtmatthijsvandermaasback5.Repository;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByRoleName(String roleName);
}
