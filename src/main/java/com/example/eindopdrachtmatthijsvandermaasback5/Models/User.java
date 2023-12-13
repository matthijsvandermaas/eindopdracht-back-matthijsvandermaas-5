package com.example.eindopdrachtmatthijsvandermaasback5.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String password;


    //    Relation with Role ManyToMany.
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    //    Relation with Profile OneToOne.
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    Profile profile;


}
