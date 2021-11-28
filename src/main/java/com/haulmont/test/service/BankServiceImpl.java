package com.haulmont.test.service;

import com.haulmont.test.dao.BankRepository;
import com.haulmont.test.entity.Bank;
import com.haulmont.test.entity.Client;
import com.haulmont.test.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public Bank findById(UUID theId) {
        Optional<Bank> result = bankRepository.findById(theId);

        Bank theBank = null;

        if (result.isPresent()) {
            theBank = result.get();
        } else {
            throw new RuntimeException("Did not find bank id - " + theId);
        }
        return theBank;
    }

    @Override
    public void save(Bank theBank) {
        bankRepository.save(theBank);
    }

    @Override
    public void deleteById(UUID theId) {
        bankRepository.deleteById(theId);
    }

}
