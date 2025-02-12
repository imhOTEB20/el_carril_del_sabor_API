package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_registers")
public class AdminRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin_register")
    private Long id;

    @NotBlank
    private String action;

    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "administrator", referencedColumnName = "id_admin")
    private Administrator administrator;

    public Long getId() {
        return id;
    }

    public @NotBlank String getAction() {
        return action;
    }

    public void setAction(@NotBlank String action) {
        this.action = action;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
