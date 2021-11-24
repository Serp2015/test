create table BANK
(
    ID BINARY(255) not null
        primary key,
    TITLE VARCHAR(255)
);

create table CLIENT
(
    ID BINARY(255) not null
        primary key,
    EMAIL VARCHAR(255),
    FULL_NAME VARCHAR(255),
    PASSPORT_NUMBER INTEGER,
    PHONE_NUMBER VARCHAR(255)
);

create table BANK_CLIENT
(
    BANK_ID BINARY(255) not null
        constraint FKO83CD4U73O6OO0XV4IFDVMJ68
            references BANK,
    CLIENT_ID BINARY(255) not null
        constraint FK5HHV8N3QSEJATVL50CXV2EJKU
            references CLIENT
);

create table BANK_ID
(
    BANK_ID BINARY(255)
        constraint FK23CYHT9YSL3JRCP7VGJ75RJXS
            references BANK,
    ID BINARY(255) not null
        primary key
        constraint FK246ME3OX93UQ27S8XOCVEPJSS
            references CLIENT
);

create table CREDIT
(
    ID BINARY(255) not null
        primary key,
    INTEREST_RATE DOUBLE,
    LIMIT BIGINT,
    TITLE VARCHAR(255)
);

create table BANK_CREDIT
(
    BANK_ID BINARY(255) not null
        constraint FKOTD3N856IFSF5K0TEYWUAE5FI
            references BANK,
    CREDIT_ID BINARY(255) not null
        constraint FKFC4OYGP67UPOV1KIR79ML4HQA
            references CREDIT
);

create table SCHEDULE
(
    ID BINARY(255) not null
        primary key,
    BODY_SUM NUMERIC(19,2),
    INTEREST_SUM NUMERIC(19,2),
    PAYMENT_DATE DATE,
    PAYMENT_SUM NUMERIC(19,2)
);

create table OFFER
(
    ID BINARY(255) not null
        primary key,
    CREDIT_SUM NUMERIC(19,2),
    CREDIT_TERM INTEGER,
    CLIENT_ID BINARY(255)
        constraint FKQH66WUQH9UB2OQTULWTFNHI1N
            references CLIENT,
    CREDIT_ID BINARY(255)
        constraint FKLRBDWF6ATJX5KF10H12IGMXRJ
            references CREDIT,
    SCHEDULE_ID BINARY(255)
        constraint FK1N9C3WR4XKDANJAHW3VELWHHQ
            references SCHEDULE
);

