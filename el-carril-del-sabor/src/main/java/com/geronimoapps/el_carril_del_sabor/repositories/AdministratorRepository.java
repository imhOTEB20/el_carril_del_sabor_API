package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.Administrator;
import com.geronimoapps.el_carril_del_sabor.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
    Optional<Administrator> findByUser(User user);
}
