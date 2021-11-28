CREATE TABLE bank
(
    id    UUID NOT NULL,
    title VARCHAR(255),
    CONSTRAINT pk_bank PRIMARY KEY (id)
);

CREATE TABLE client
(
    id              UUID NOT NULL,
    full_name       VARCHAR(255),
    phone_number    VARCHAR(255),
    email           VARCHAR(255),
    passport_number INT,
    bank_id         UUID,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

ALTER TABLE client
    ADD CONSTRAINT FK_CLIENT_ON_BANK FOREIGN KEY (bank_id) REFERENCES bank (id);

CREATE TABLE credit
(
    id            UUID NOT NULL,
    title         VARCHAR(255),
    limit         BIGINT,
    interest_rate DOUBLE,
    bank_id       UUID,
    CONSTRAINT pk_credit PRIMARY KEY (id)
);

ALTER TABLE credit
    ADD CONSTRAINT FK_CREDIT_ON_BANK FOREIGN KEY (bank_id) REFERENCES bank (id);

CREATE TABLE offer
(
    id          UUID NOT NULL,
    credit_sum  DECIMAL,
    credit_term INT,
    client_id   UUID,
    credit_id   UUID,
    CONSTRAINT pk_offer PRIMARY KEY (id)
);

ALTER TABLE offer
    ADD CONSTRAINT FK_OFFER_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE offer
    ADD CONSTRAINT FK_OFFER_ON_CREDIT FOREIGN KEY (credit_id) REFERENCES credit (id);

CREATE TABLE schedule
(
    id           UUID NOT NULL,
    payment_date date,
    payment_sum  DECIMAL,
    body_sum     DECIMAL,
    interest_sum DECIMAL,
    offer_id     UUID,
    CONSTRAINT pk_schedule PRIMARY KEY (id)
);

ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_OFFER FOREIGN KEY (offer_id) REFERENCES offer (id);

