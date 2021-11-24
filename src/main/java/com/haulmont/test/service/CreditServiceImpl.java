package com.haulmont.test.service;

import com.haulmont.test.dao.CreditRepository;
import com.haulmont.test.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditServiceImpl implements CreditService {

    private CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public List<Credit> findAll() {
        return creditRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public Credit findById(UUID theId) {
        return creditRepository.findCreditById(theId);
    }

    @Override
    public void save(Credit theCredit) {
        creditRepository.save(theCredit);
    }

    @Override
    public void deleteById(UUID theId) {
        creditRepository.deleteById(theId);
    }
}
