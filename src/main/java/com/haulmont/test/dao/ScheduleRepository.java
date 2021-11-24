package com.haulmont.test.dao;

import com.haulmont.test.entity.Offer;
import com.haulmont.test.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    List<Schedule> findAllByOffer(UUID uuid);
//    List<Schedule> findAllByOrderByPaymentDate(UUID id);
    List<Schedule> findAllByOfferOrderByPaymentDate(UUID id);
}
