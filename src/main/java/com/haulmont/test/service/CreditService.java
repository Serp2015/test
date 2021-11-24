package com.haulmont.test.service;

import com.haulmont.test.entity.Credit;

import java.util.List;
import java.util.UUID;

public interface CreditService {

    List<Credit> findAll();

    Credit findById(UUID theId);

    void save(Credit theCredit);

    void deleteById(UUID theId);
}
