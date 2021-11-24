package com.haulmont.test.service;

import com.haulmont.test.dao.OfferRepository;
import com.haulmont.test.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAllByOrderByCreditSumAsc();
    }

    @Override
    public Offer findById(UUID theId) {
        return offerRepository.findOfferById(theId);
    }

    @Override
    public void save(Offer theOffer) {
        offerRepository.save(theOffer);
    }

    @Override
    public void deleteById(UUID theId) {
        offerRepository.deleteById(theId);
    }

}
