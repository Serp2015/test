package com.haulmont.test.entity;

import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    @NotEmpty(message = "Put title")
    private String title;

    @Column(name = "limit")
    @Min(value = 100, message = "Limit should be more then 100")
    private long limit;

    @Column(name = "interest_rate")
    @Min(value = 1, message = "Interest rate should be more then 1%")
    private double interestRate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Offer> offers;

    public Credit() {
    }

    public Credit(String title, long limit, double interestRate, Bank bank, List<Offer> offers) {
        this.title = title;
        this.limit = limit;
        this.interestRate = interestRate;
        this.bank = bank;
        this.offers = offers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
