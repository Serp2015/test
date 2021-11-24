package com.haulmont.test.dao;

import com.haulmont.test.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {

    List<Bank> findAllByOrderByTitleAsc();
    Bank findBankById(UUID theId);
}
