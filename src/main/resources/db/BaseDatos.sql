create database bank;
set search_path = "public";


DROP TABLE IF EXISTS Movement;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Person;

CREATE TABLE IF NOT EXISTS Person
(
    id                SERIAL PRIMARY KEY NOT NULL,
    name              VARCHAR(100)       NOT NULL,
    gender            VARCHAR(50)        NOT NULL,
    age               INTEGER            NOT NULL,
    identification    VARCHAR(20)        NOT NULL
        CONSTRAINT person_unique_identification_constraint
            UNIQUE,
    address           VARCHAR(200)       NOT NULL,
    phone             VARCHAR(20)        NOT NULL
);


CREATE TABLE IF NOT EXISTS Customer
(
    customer_id       SERIAL PRIMARY KEY NOT NULL,
    person_id         INTEGER            NOT NULL,
    password          VARCHAR(100)       NOT NULL,
    status            VARCHAR(20)        NOT NULL,
    FOREIGN KEY (person_id) REFERENCES Person (id)
);


CREATE TABLE IF NOT EXISTS Account
(
    id                     SERIAL PRIMARY KEY NOT NULL,
    customer_id            INTEGER            NOT NULL,
    number                 VARCHAR(50)        NOT NULL
        CONSTRAINT account_unique_number_constraint
            UNIQUE,
    type                   VARCHAR(20)        NOT NULL,
    max_daily_debit_amount NUMERIC(15, 2)     NOT NULL,
    initial_balance        NUMERIC(15, 2)     NOT NULL,
    balance                NUMERIC(15, 2)     NOT NULL,
    status                 VARCHAR(20)        NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer (customer_id)
);


CREATE TABLE IF NOT EXISTS Movement
(
    id                SERIAL PRIMARY KEY NOT NULL,
    date              TIMESTAMP          NOT NULL,
    type              VARCHAR(20)        NOT NULL,
    value             NUMERIC(15, 2)     NOT NULL,
    balance           NUMERIC(15, 2)     NOT NULL,
    account_id        INTEGER            NOT NULL REFERENCES Account (id)
);

