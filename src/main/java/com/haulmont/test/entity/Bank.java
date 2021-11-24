package com.haulmont.test.entity;

import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "bank_client", joinColumns = @JoinColumn(name = "bank_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> clients;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "bank_credit", joinColumns = @JoinColumn(name = "bank_id"), inverseJoinColumns = @JoinColumn(name = "credit_id"))
    private List<Credit> credits;

    public Bank() {
    }

    public Bank(String title) {
        this.title = title;
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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }
}
