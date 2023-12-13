package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;


import com.example.eindopdrachtmatthijsvandermaasback5.DTO.RoleDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> rdto = roleService.getRoles();
        return new ResponseEntity<>(rdto, HttpStatus.OK);

    }
}
