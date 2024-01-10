package com.example.eindopdrachtmatthijsvandermaasback5.Repository;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUsername(String username);

}
