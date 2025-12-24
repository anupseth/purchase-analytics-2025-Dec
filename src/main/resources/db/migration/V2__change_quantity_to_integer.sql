-- Migrate numeric quantity to integer to match JPA entity
-- Safely round values when converting
BEGIN;
ALTER TABLE receipt_items
    ALTER COLUMN quantity TYPE integer USING round(quantity)::integer;
COMMIT;