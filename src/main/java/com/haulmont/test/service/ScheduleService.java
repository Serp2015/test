package com.haulmont.test.service;

import com.haulmont.test.entity.Offer;
import com.haulmont.test.entity.Schedule;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {

    List<Schedule> findAll(int termInMonth, double interestRate, BigDecimal creditSum, Offer offer);

    void saveAll(List<Schedule> theSchedules);
    List<Schedule> findAllByOffer(UUID id);
    List<Schedule> findAllByOrderByPaymentDate(UUID id);
}
