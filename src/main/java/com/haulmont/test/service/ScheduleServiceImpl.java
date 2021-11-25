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
        double interestRateHere = interestRate / 12 / 100;
        BigDecimal monthPayment = creditSum.multiply(BigDecimal.valueOf(
                        interestRateHere + (interestRateHere / (Math.pow((1 + interestRateHere), termInMonth) - 1))))
                .setScale(2, RoundingMode.CEILING);
        LocalDate date = LocalDate.now();
        for (int i = 0; i < termInMonth; i++) {
            Schedule schedule = new Schedule();
            schedule.setPaymentSum(monthPayment);
            schedule.setPaymentDate(Date.valueOf(date));
            BigDecimal interestSum = creditSum.multiply(BigDecimal.valueOf(interestRateHere))
                    .setScale(2, RoundingMode.CEILING);
            schedule.setInterestSum(interestSum);
            BigDecimal bodySum = monthPayment.subtract(interestSum)
                    .setScale(2, RoundingMode.CEILING);
            schedule.setBodySum(bodySum);
            schedules.add(schedule);
            schedule.setOffer(offer);
            date = date.plusMonths(1);
            creditSum = creditSum.subtract(bodySum);
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
