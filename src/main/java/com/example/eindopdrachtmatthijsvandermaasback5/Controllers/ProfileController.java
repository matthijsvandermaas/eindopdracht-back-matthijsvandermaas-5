package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;

import com.example.eindopdrachtmatthijsvandermaasback5.DTO.ProfileAndUserDto;
import com.example.eindopdrachtmatthijsvandermaasback5.DTO.ProfileDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.IdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/profile")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfile() {
        List<ProfileDto> pDto = profileService.getAllProfile();
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getOneProfile(@PathVariable Long id) {
        ProfileDto pDto = profileService.getProfile(id);
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@Valid @RequestBody ProfileAndUserDto profileAndUserDto) {
        ProfileDto pdto = profileService.createProfile(profileAndUserDto);
        return new ResponseEntity<>(pdto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileDto> deleteProfile(@PathVariable Long id) {
        try {
            profileService.deleteProfile(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
