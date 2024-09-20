CREATE TABLE IF NOT EXISTS credit_card_directory
(
    id             TEXT NOT NULL,
    credit_card    TEXT NOT NULL,
    classpath      TEXT NOT NULL,
    file_extension TEXT NOT NULL,
    name_pattern   TEXT NOT NULL,
    version        INTEGER NULL,
    UNIQUE (credit_card),
    CONSTRAINT "PKCardDirectory_01" PRIMARY KEY ("id")
);


CREATE TABLE IF NOT EXISTS transaction_category
(
    id          TEXT NOT NULL,
    category    TEXT NOT NULL,
    description TEXT NULL,
    version     INTEGER NULL,
    UNIQUE (category),
    CONSTRAINT "PKTransactionCategory_01" PRIMARY KEY ("id")
);


CREATE TABLE IF NOT EXISTS credit_card_transaction
(
    id                       TEXT           NOT NULL,
    transaction_date         TIMESTAMP      WITHOUT TIME ZONE,
    statement_year           INTEGER        NOT NULL,
    statement_month          INTEGER        NOT NULL,
    charge_card              TEXT           NOT NULL,
    description              TEXT           NOT NULL,
    transaction_amount       NUMERIC(10, 2) NOT NULL,
    transaction_category_id  TEXT           NULL,
    version                  INTEGER        NULL,
    CONSTRAINT "PKCardTransaction_01" PRIMARY KEY ("id"),
    CONSTRAINT "FKCardTransaction_01"
        FOREIGN KEY (transaction_category_id)
            REFERENCES transaction_category (id)
            ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS "IDXCardTransaction_02"
    on credit_card_transaction (statement_year);

CREATE INDEX IF NOT EXISTS "IDXCardTransaction_03"
    on credit_card_transaction (statement_month);

CREATE INDEX IF NOT EXISTS "IDXCardTransaction_04"
    on credit_card_transaction (transaction_category_id);
