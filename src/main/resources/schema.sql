CREATE TABLE IF NOT EXISTS credit_card_transaction
(
    id                  TEXT           NOT NULL,
    transaction_date    TIMESTAMP      WITHOUT TIME ZONE,
    statement_year      INTEGER        NOT NULL,
    statement_month     INTEGER        NOT NULL,
    charge_card         TEXT           NOT NULL,
    description         TEXT           NOT NULL,
    transaction_amount  NUMERIC(10, 2) NOT NULL,
    category            TEXT           NULL,
    version             INTEGER        NULL,
    CONSTRAINT "PKCardTransaction_01" PRIMARY KEY ("id")
);

CREATE INDEX IF NOT EXISTS "IDXCardTransaction_02"
    on credit_card_transaction (statement_year);

CREATE INDEX IF NOT EXISTS "IDXCardTransaction_03"
    on credit_card_transaction (statement_month);

CREATE INDEX IF NOT EXISTS "IDXCardTransaction_04"
    on credit_card_transaction (category);

