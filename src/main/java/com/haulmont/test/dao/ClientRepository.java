package com.haulmont.test.dao;

import com.haulmont.test.entity.Bank;
import com.haulmont.test.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findAllByOrderByFullNameAsc();
    Client findClientById(UUID theId);
}
