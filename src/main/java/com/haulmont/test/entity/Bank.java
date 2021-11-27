package com.haulmont.test.entity;

import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
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
    @NotEmpty(message = "Title should not be empty")
    private String title;

    @OneToMany(mappedBy = "bank", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Client> clients;

    @OneToMany(mappedBy = "bank", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
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

    public void addCredit(Credit credit) {
        if (credits == null) {
            credits = new ArrayList<>();
        }
        if (!credits.contains(credit)) {
            credits.add(credit);
        }
        credit.setBank(this);
    }

    public void addClient(Client client) {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (!clients.contains(client)) {
            clients.add(client);
        }
        client.setBank(this);
    }
}
