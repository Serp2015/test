package com.haulmont.test.service;

import com.haulmont.test.entity.Bank;

import java.util.List;
import java.util.UUID;

public interface BankService {

    List<Bank> findAll();

    Bank findById(UUID theId);

    void save(Bank theBank);

    void deleteById(UUID theId);
}
