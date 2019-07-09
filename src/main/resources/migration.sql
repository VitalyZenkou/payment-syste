CREATE DATABASE payment_system;

CREATE SCHEMA payment_system_storage;

SET SEARCH_PATH = payment_system_storage;

CREATE TABLE "user"
(
    id         BIGSERIAL PRIMARY KEY,
    login      VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(300) NOT NULL,
    name       VARCHAR(50)  NOT NULL,
    surname    VARCHAR(50)  NOT NULL,
    birth_date DATE         NOT NULL
);

CREATE TABLE role
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    FOREIGN KEY (role_id) REFERENCES role (id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE address
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT       NOT NULL,
    city         VARCHAR(50)  NOT NULL,
    street       VARCHAR(128) NOT NULL,
    house_number INT          NOT NULL,
    flat_number  INT,
    phone_number VARCHAR(20),
    email        VARCHAR(100) NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    UNIQUE (city, street, house_number, phone_number)
);

CREATE TABLE bank_account
(
    id         BIGSERIAL PRIMARY KEY,
    version    BIGINT,
    user_id    BIGINT         NOT NULL,
    balance    DECIMAL(10, 2) NOT NUlL DEFAULT 0.00,
    is_blocked BOOLEAN        NOT NULL DEFAULT false,
    currency   VARCHAR(3)     NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE credit_card
(
    id               BIGSERIAL PRIMARY KEY,
    account_id       BIGINT      NOT NULL,
    number           VARCHAR(50) NOT NULL UNIQUE,
    validity_date    VARCHAR(50) NOT NULL,
    pin_code         INTEGER    NOT NULL,
    cvv              INTEGER    NOT NULL,
    is_blocked       BOOLEAN     NOT NULL DEFAULT false,
    credit_card_type VARCHAR(20) NOT NULL,
    FOREIGN KEY (account_id) REFERENCES bank_account (id)
);

CREATE TABLE payment
(
    id                    BIGSERIAL PRIMARY KEY,
    user_id               BIGINT         NOT NULL,
    credit_card_id        BIGINT         NOT NULL,
    cost                  DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    organization          VARCHAR(128)   NOT NULL DEFAULT '',
    to_credit_card_number VARCHAR(50)    NOT NULL DEFAULT '',
    is_transaction        BOOLEAN        NOT NULL DEFAULT false,
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    FOREIGN KEY (credit_card_id) REFERENCES credit_card (id)
);