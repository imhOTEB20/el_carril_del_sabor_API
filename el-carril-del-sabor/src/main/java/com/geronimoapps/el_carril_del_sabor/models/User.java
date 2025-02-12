package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String auth0Id;

    @NotBlank
    private String email;

    @Enumerated
    private UserRole userRole;

    public Long getId() {
        return id;
    }

    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
