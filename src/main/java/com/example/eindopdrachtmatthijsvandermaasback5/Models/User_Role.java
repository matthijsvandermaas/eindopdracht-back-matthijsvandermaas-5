package com.example.eindopdrachtmatthijsvandermaasback5.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class User_Role {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")
    private Role role;

    public void setUsername(String username) {
        this.user.setUsername(username);
    }

    public String[] toArray(String[] strings) {
        return strings;
    }
}
