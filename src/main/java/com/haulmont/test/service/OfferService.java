package com.haulmont.test.service;

import com.haulmont.test.entity.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferService {

    List<Offer> findAll();

    Offer findById(UUID theId);

    void save(Offer theOffer);

    void deleteById(UUID theId);

}
