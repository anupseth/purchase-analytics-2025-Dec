-- =========================
-- STORES
-- =========================
CREATE TABLE stores (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    country         VARCHAR(50)  NOT NULL,
    created_at      TIMESTAMP    NOT NULL DEFAULT now()
);

-- =========================
-- CATEGORIES
-- =========================
CREATE TABLE categories (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL UNIQUE,
    created_at      TIMESTAMP    NOT NULL DEFAULT now()
);

-- =========================
-- RECEIPTS
-- =========================
CREATE TABLE receipts (
    id              BIGSERIAL PRIMARY KEY,
    store_id        BIGINT       NOT NULL,
    receipt_date    DATE         NOT NULL,
    total_amount    NUMERIC(10,2) NOT NULL,
    currency        VARCHAR(10)  NOT NULL,
    created_at      TIMESTAMP    NOT NULL DEFAULT now(),

    CONSTRAINT fk_receipt_store
        FOREIGN KEY (store_id) REFERENCES stores(id)
);

-- =========================
-- RECEIPT ITEMS
-- =========================
CREATE TABLE receipt_items (
    id              BIGSERIAL PRIMARY KEY,
    receipt_id      BIGINT       NOT NULL,
    category_id     BIGINT       NULL,
    original_name   VARCHAR(255) NOT NULL,
    normalized_name VARCHAR(255),
    quantity        NUMERIC(10,2),
    price           NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_item_receipt
        FOREIGN KEY (receipt_id) REFERENCES receipts(id),

    CONSTRAINT fk_item_category
        FOREIGN KEY (category_id) REFERENCES categories(id)
);
