CREATE TABLE address (
    id                 BIGSERIAL PRIMARY KEY,
    street             VARCHAR(256) NOT NULL,
    city               VARCHAR(256) NOT NULL,
    zip_code           VARCHAR(32) NOT NULL,
    state              VARCHAR(256),
    country            VARCHAR(256) NOT NULL,
    street_number      VARCHAR(64) NOT NULL,
    apartment_number   VARCHAR(64),

    created_at         TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT NOT NULL
);

CREATE TABLE address_change_log (
    id                  BIGSERIAL PRIMARY KEY,
    old_address_id      BIGINT,
    new_address_id      BIGINT NOT NULL,
    changed_by          BIGINT NOT NULL,
    changed_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);