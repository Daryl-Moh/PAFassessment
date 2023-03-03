drop database if exists acme_bank;

create database acme_bank;

CREATE TABLE accounts (
    account_id char(10) NOT NULL,
    name varchar(128) NOT NULL,
    balance decimal(2,2) NOT NULL,
    constraint pk_account_id primary key(account_id)

    insert into account_id (account_id, name, balance)
    values 
    (V9L3Jd1BBI, fred, 100.00),
    (fhRq46Y6vB, barney, 300.00),
    (uFSFRqUpJy, wilma, 1000.00),
    (ckTV56axff, betty, 1000.00),
    (Qgcnwbshbh, pebbles, 50.00),
    (if9l185l18, bambam, 50.00);
);


