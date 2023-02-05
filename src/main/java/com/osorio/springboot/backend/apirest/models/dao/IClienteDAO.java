package com.osorio.springboot.backend.apirest.models.dao;

import com.osorio.springboot.backend.apirest.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteDAO extends JpaRepository<Cliente, Long> {
}
