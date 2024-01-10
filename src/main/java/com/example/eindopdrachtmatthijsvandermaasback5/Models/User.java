package com.example.eindopdrachtmatthijsvandermaasback5.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "username")
    private String username;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String password;


    //    Relation with Role ManyToMany.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "role_name")
    )
    private Role role;


    public List<Role> getRoles() {
        if (role != null) {
            return List.of(role);
        } else {
            return Collections.emptyList();
        }
    }


    public void setRoles(Role role) {
        if (this.role == null) {
            this.role = role;
        } else {
            System.out.println("De gebruiker heeft al een rol toegewezen gekregen.");
        }
    }


    public void addRole(Role role) {
        if (this.role == null) {
            this.role = role;
        } else {
            this.role = role;
        }
    }
}



