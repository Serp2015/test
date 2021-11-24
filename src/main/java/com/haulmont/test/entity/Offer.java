package com.haulmont.test.entity;

import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "credit_sum")
    private BigDecimal creditSum;

    @Column(name = "credit_term")
    private int termInMonth;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    public Offer() {
    }

    public Offer(BigDecimal creditSum, int termInMonth, Client client, Credit credit, List<Schedule> schedules) {
        this.creditSum = creditSum;
        this.termInMonth = termInMonth;
        this.client = client;
        this.credit = credit;
        this.schedules = schedules;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(BigDecimal creditSum) {
        this.creditSum = creditSum;
    }

    public int getTermInMonth() {
        return termInMonth;
    }

    public void setTermInMonth(int termInMonth) {
        this.termInMonth = termInMonth;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
