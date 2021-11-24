package com.haulmont.test.service;

import com.haulmont.test.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<Client> findAll();

    Client findById(UUID theId);

    void save(Client theClient);

    void deleteById(UUID theId);
}
