package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.AdminRegister;
import com.geronimoapps.el_carril_del_sabor.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRegisterRepository extends JpaRepository<AdminRegister, Long> {
    List<AdminRegister> findByAdministrator(Administrator admin);
}
