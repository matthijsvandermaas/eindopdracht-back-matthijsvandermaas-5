package com.example.eindopdrachtmatthijsvandermaasback5.Service;


import com.example.eindopdrachtmatthijsvandermaasback5.DTO.RoleDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.Role;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository repos;

    public RoleService(RoleRepository repos) {
        this.repos = repos;
    }

    public List<RoleDto> getRoles() {


        List<RoleDto> roleDtos = new ArrayList<>();

        for (Role r : repos.findAll()) {
            RoleDto rdto = new RoleDto();
            rdto.setRoleName(r.getRoleName());

            roleDtos.add(rdto);
        }
        return roleDtos;
    }
}