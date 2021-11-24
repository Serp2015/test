package com.haulmont.test.service;

import com.haulmont.test.dao.ClientRepository;
import com.haulmont.test.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAllByOrderByFullNameAsc();
    }

    @Override
    public Client findById(UUID theId) {
        return clientRepository.findClientById(theId);
    }

    @Override
    public void save(Client theClient) {
        clientRepository.save(theClient);
    }

    @Override
    public void deleteById(UUID theId) {
        clientRepository.deleteById(theId);
    }
}
