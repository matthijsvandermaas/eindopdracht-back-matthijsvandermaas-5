package com.example.eindopdrachtmatthijsvandermaasback5.Service;


import com.example.eindopdrachtmatthijsvandermaasback5.DTO.ProfileAndUserDto;
import com.example.eindopdrachtmatthijsvandermaasback5.DTO.ProfileDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.IdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.Profile;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.ProfileRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.RoleRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;


    public ProfileService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.profileRepository = profileRepository;
    }


    public List<ProfileDto> getAllProfile() {
        List<Profile> profile = profileRepository.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();

        for (Profile p : profile) {
            ProfileDto pDto = new ProfileDto();
            profileToProfileDto(p, pDto);

            profileDtos.add(pDto);
        }
        return profileDtos;
    }

    private static void profileToProfileDto(Profile p, ProfileDto pDto) {
        pDto.setFirstName(p.getFirstName());
        pDto.setLastName(p.getLastName());
        pDto.setEmail(p.getEmail());
        pDto.setId(p.getId());
    }

    private void profileDtoToProfile(ProfileDto pDto, Profile p) {
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
    }

    public ProfileDto getProfile(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            Profile p = profile.get();
            ProfileDto pDto = new ProfileDto();
            profileToProfileDto(p, pDto);
            return (pDto);
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
        }
    }

    public ProfileDto createProfile(@Valid ProfileAndUserDto profileAndUserDto) {
        Profile profile = new Profile();
        profileAndUserDtoToProfile(profileAndUserDto, profile);


        Profile savedProfile = profileRepository.save(profile);

        // Create and return a ProfileDto based on the savedProfile
        ProfileDto savedProfileDto = new ProfileDto();
        profileToProfileDto(savedProfile, savedProfileDto);

        return savedProfileDto;
    }

    private void profileAndUserDtoToProfile(ProfileAndUserDto pDto, Profile p) {
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
    }

    public String deleteProfile(@RequestBody Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
        } else {
            throw new IdNotFoundException("Profile not found with ID: " + id);
        }

        return "Profile deleted";
    }

}
