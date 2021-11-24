package com.haulmont.test.service;

import com.haulmont.test.dao.ScheduleRepository;
import com.haulmont.test.entity.Offer;
import com.haulmont.test.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> findAll(int termInMonth, double interestRate, BigDecimal creditSum, Offer offer) {

        List<Schedule> schedules = new ArrayList<>();
        BigDecimal bodySum = creditSum.divide(BigDecimal.valueOf(termInMonth), 2,
                RoundingMode.CEILING);
        LocalDate date = LocalDate.now();

        for (int i = 0; i < termInMonth; i++) {
            BigDecimal interestSum = creditSum.divide(BigDecimal.valueOf(interestRate), 2)
                    .setScale(2, RoundingMode.CEILING);
            creditSum = creditSum.subtract(bodySum)
                    .setScale(2, RoundingMode.CEILING);
            BigDecimal paymentSum = interestSum.add(bodySum)
                    .setScale(2, RoundingMode.CEILING);
            Schedule schedule = new Schedule();
            schedule.setBodySum(bodySum);
            schedule.setPaymentSum(paymentSum);
            schedule.setInterestSum(interestSum);
            schedule.setOffer(offer);
            schedule.setPaymentDate(Date.valueOf(date.plusMonths(i)));
            schedules.add(schedule);
        }

        return schedules;
    }

    @Override
    public void saveAll(List<Schedule> theSchedules) {
        scheduleRepository.saveAll(theSchedules);
    }

    @Override
    public List<Schedule> findAllByOffer(UUID id) {
        return scheduleRepository.findAllByOfferOrderByPaymentDate(id);
    }

    @Override
    public List<Schedule> findAllByOrderByPaymentDate(UUID id) {
        return scheduleRepository.findAllByOfferOrderByPaymentDate(id);
    }
}
