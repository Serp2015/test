package com.haulmont.test.dao;

import com.haulmont.test.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {

    List<Offer> findAllByOrderByCreditSumAsc();
    Offer findOfferById(UUID theId);
}
