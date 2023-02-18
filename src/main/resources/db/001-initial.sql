create database bank;
set search_path = "public";


DROP TABLE IF EXISTS Movement;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS People;

CREATE TABLE IF NOT EXISTS People
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    gender         VARCHAR(10),
    age            INTEGER,
    identification VARCHAR(20),
    address        VARCHAR(200),
    phone          VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS Customer
(
    customer_id SERIAL PRIMARY KEY,
    person_id   INTEGER,
    password    VARCHAR(100),
    status      VARCHAR(20),
    FOREIGN KEY (person_id) REFERENCES People (id)
);


CREATE TABLE IF NOT EXISTS Account
(
    id                     SERIAL PRIMARY KEY,
    customer_id            INTEGER NOT NULL,
    number                 VARCHAR(50) UNIQUE,
    type                   VARCHAR(20),
    max_daily_debit_amount NUMERIC(15, 2),
    initial_balance        NUMERIC(15, 2),
    balance                NUMERIC(15, 2),
    status                 VARCHAR(20),

    FOREIGN KEY (customer_id) REFERENCES People (id)
);


CREATE TABLE IF NOT EXISTS Movement
(
    id         SERIAL PRIMARY KEY,
    date       TIMESTAMP,
    type       VARCHAR(20),
    value      NUMERIC(15, 2),
    balance    NUMERIC(15, 2),
    account_id INTEGER REFERENCES Account (id)
);

