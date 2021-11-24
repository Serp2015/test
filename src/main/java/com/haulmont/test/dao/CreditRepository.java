package com.haulmont.test.dao;

import com.haulmont.test.entity.Client;
import com.haulmont.test.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {

    List<Credit> findAllByOrderByTitleAsc();
    Credit findCreditById(UUID id);
}
