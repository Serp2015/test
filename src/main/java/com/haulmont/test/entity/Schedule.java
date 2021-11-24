package com.haulmont.test.entity;

import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Column(name = "payment_sum")
    private BigDecimal paymentSum;

    @Column(name = "body_sum")
    private BigDecimal bodySum;

    @Column(name = "interest_sum")
    private BigDecimal interestSum;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "offer_id")
    private Offer offer;

    public Schedule() {
    }

    public Schedule(Date paymentDate, BigDecimal paymentSum, BigDecimal bodySum, BigDecimal interestSum, Offer offer) {
        this.paymentDate = paymentDate;
        this.paymentSum = paymentSum;
        this.bodySum = bodySum;
        this.interestSum = interestSum;
        this.offer = offer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }

    public BigDecimal getBodySum() {
        return bodySum;
    }

    public void setBodySum(BigDecimal bodySum) {
        this.bodySum = bodySum;
    }

    public BigDecimal getInterestSum() {
        return interestSum;
    }

    public void setInterestSum(BigDecimal interestSum) {
        this.interestSum = interestSum;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
