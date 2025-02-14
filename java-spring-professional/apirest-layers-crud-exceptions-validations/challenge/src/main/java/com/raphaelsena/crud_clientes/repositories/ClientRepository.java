package com.raphaelsena.crud_clientes.repositories;

import com.raphaelsena.crud_clientes.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
